package cn.obanks.service;

import java.util.List;
import cn.obanks.model.Authority;
import cn.obanks.model.RoleResource;
import cn.obanks.model.UserDetailsWrapper;

public interface AuthorityService {
	void grant(Long userId, List<Long> roles, Long opr);

	List<RoleResource> getRoleResource(String appId);

	UserDetailsWrapper getUserDetails(String acctCode);

	List<Authority> getGrantedAuthorities(long userId);

	void remove(Authority authority);

	void grant(Authority authority, Long opr);
}
