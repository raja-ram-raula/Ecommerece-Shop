package in.nareshit.raghu.util;

import java.security.Principal;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {

	public boolean isUserLoggedIn(Principal p) {
		return (p!=null && p.getName()!=null) ? true:false;
	}
}
