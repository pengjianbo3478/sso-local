package cn.obanks.security.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import cn.obanks.model.UserDetailsWrapper;
import cn.obanks.service.AuthorityService;

public class OBUserDetailsService implements UserDetailsService {
	private static final Logger LOG = LoggerFactory.getLogger(OBUserDetailsService.class);
	private final String appId;
	@Autowired
	private AuthorityService service;

	public OBUserDetailsService(final String appId) {
		this.appId = appId;
		LOG.debug("create SensunUserDetailsService with appId " + appId);
	}

	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		LOG.debug("loadUserByUsername " + username + "|appId=" + appId);
		UserDetailsWrapper details = service.getUserDetails(username);
		final String password = details.getPassword();
		final List<String> grantedAuthorities = details.getGrantedAuthorities();
		final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (!CollectionUtils.isEmpty(grantedAuthorities)) {
			for (final String grantedAuthority : grantedAuthorities) {
				authorities.add(new GrantedAuthority() {
					private static final long serialVersionUID = 1L;

					@Override
					public String getAuthority() {
						return grantedAuthority;
					}
				});
			}
		}
		LOG.debug("authorities " + authorities);
		long userId = details.getUserId();
		OBUserDetails obUserDetails = new OBUserDetails(userId, username, password, authorities);
		return obUserDetails;
	}
}
