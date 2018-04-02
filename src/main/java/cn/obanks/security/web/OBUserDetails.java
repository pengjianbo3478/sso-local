package cn.obanks.security.web;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Winnie
 * @since 1.0
 */
public class OBUserDetails implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	private final String username;
	private final Long userId;
	private final String password;

	private final Collection<GrantedAuthority> authorities;

	public OBUserDetails(final Long userId, final String username, final String password, final Collection<GrantedAuthority> authorities) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.authorities = authorities;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public Long getUserId() {
		return userId;
	}

	/**   
	 * <p>Title: isAccountNonExpired</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()   
	 */ 
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**   
	 * <p>Title: isAccountNonLocked</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()   
	 */ 
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**   
	 * <p>Title: isCredentialsNonExpired</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()   
	 */ 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**   
	 * <p>Title: isEnabled</p>   
	 * <p>Description: </p>   
	 * @return   
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()   
	 */ 
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
