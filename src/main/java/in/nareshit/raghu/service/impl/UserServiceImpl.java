package in.nareshit.raghu.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.constants.UserStatus;
import in.nareshit.raghu.entity.User;
import in.nareshit.raghu.repo.UserRepository;
import in.nareshit.raghu.service.IUserService;
import in.nareshit.raghu.util.AppUtil;
import in.nareshit.raghu.util.MyMailUtil;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MyMailUtil mailUtil;
	
	@Override
	public Long saveUser(User user) {
		// Generating password
		String pwd = AppUtil.genPwd();
		
		//read generated pwd and encode
		String encPwd = encoder.encode(pwd);
		//set back to user object
		user.setPassword(encPwd);
		
		user.setStatus(UserStatus.INACTIVE.name());
		Long id = repo.save(user).getId();
		
		if(id!=null) { //only on user register (send email as a thread)
			new Thread(new Runnable() {
				public void run() {
					String text = "User is created! username : " + user.getEmail() 
					+ ", password:" + pwd
					+ ", with role: " + user.getRole().name();
					mailUtil.send(user.getEmail(), "User Register", text);
				}
			}).start();
		}
		return id;
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		//load user by username(email)
		Optional<User> opt = findByEmail(username);
		if(!opt.isPresent()) {
			throw new UsernameNotFoundException("Not exist");
		} else {
			//read user object
			User user = opt.get();
			return new org.springframework.security.core.userdetails
					//username, password, List<GA>(RoleAsString)
					.User(
							user.getEmail(), 
							user.getPassword(), 
							Arrays.asList(
									new SimpleGrantedAuthority(
											user.getRole().name()
											)
									)
						);
		}
	}
	
	@Transactional
	public void updateUserPwd(String pwd, Long userId) {
		String encPwd = encoder.encode(pwd);
		repo.updateUserPwd(encPwd, userId);
	}

}
