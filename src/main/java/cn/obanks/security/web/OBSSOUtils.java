package cn.obanks.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class OBSSOUtils {
	public static final String getLoginAcctName() {
		final OBUserDetails principal = getPrincipal();
		return (null != principal) ? principal.getUsername() : null;
	}

	public static final Long getUserId() {
		final OBUserDetails principal = getPrincipal();
		return (null != principal) ? principal.getUserId() : null;
	}

	private static OBUserDetails getPrincipal() {
		final SecurityContext context = SecurityContextHolder.getContext();
		if (null != context) {
			final Authentication authentication = context.getAuthentication();
			if (null != authentication) {
				final Object principal = authentication.getPrincipal();
				if ((null != principal) && (principal.getClass().isAssignableFrom(OBUserDetails.class))) {
					return (OBUserDetails) principal;
				}
			}
		}
		return null;
	}

}