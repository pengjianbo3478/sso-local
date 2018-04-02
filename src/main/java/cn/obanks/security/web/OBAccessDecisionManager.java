package cn.obanks.security.web;
import java.util.Collection;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service("OBAccessDecisionManager")
public class OBAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(final Authentication authentication, final Object obj, final Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (null == configAttributes) {
			return;
		}
		for (ConfigAttribute configAttribute : configAttributes) {
			String needsRole = configAttribute.getAttribute();
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
				if (grantedAuthority.getAuthority().equals(needsRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("AccessDenied!!!");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
