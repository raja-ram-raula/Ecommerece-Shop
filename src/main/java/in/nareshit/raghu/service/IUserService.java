package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.raghu.entity.User;

public interface IUserService {

	Long saveUser(User user);
	Optional<User> findByEmail(String email);
	List<User> getAllUsers();
	void updateUserPwd(String pwd,Long userId);
}
