package cn.obanks.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.obanks.enums.DeletedFlag;
import cn.obanks.mapper.AuthorityMapper;
import cn.obanks.mapper.CredentialMapper;
import cn.obanks.mapper.RoleInfoMapper;
import cn.obanks.model.Authority;
import cn.obanks.model.Credential;
import cn.obanks.model.RoleInfo;
import cn.obanks.model.RoleResource;
import cn.obanks.model.UserDetailsWrapper;
import cn.obanks.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Resource
	protected RoleInfoMapper roleInfoMapper;
	@Resource
	protected AuthorityMapper authorityMapper;
	@Resource
	protected CredentialMapper credentialMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void grant(Long userId, List<Long> roles, Long opr) {
		if (userId == null) {
			throw new IllegalArgumentException("userId");
		}
		if (roles == null) {
			throw new IllegalArgumentException("roles");
		}
		if (opr == null) {
			throw new IllegalArgumentException("opr");
		}
		if (CollectionUtils.isEmpty(roles))
			return;
		Date now = new Date();
		for (Long roleId : roles) {
			Authority authority = new Authority(userId, roleId, opr, now, opr, now, DeletedFlag.NORMAL.getValue());
			this.authorityMapper.add(authority);
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<RoleResource> getRoleResource(String appId) {
		return this.roleInfoMapper.getRoleResource(appId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserDetailsWrapper getUserDetails(String acctCode) {
		final UserDetailsWrapper result = new UserDetailsWrapper();
		Credential credential = this.credentialMapper.getByAcctCode(acctCode);
		final List<RoleInfo> roles = this.roleInfoMapper.getGrantedAuthoritiesByAcctCode(acctCode);
		if (CollectionUtils.isEmpty(roles) == false) {
			final List<String> grantedAuthorities = new ArrayList<String>(roles.size());
			for (RoleInfo role : roles) {
				grantedAuthorities.add(role.getRoleCode());
			}
			result.setGrantedAuthorities(grantedAuthorities);
		}
		Long userId = credential.getUserId();
		String isAccountNonexpired = credential.getIsAccountNonexpired();
		String isAccountNonlocked = credential.getIsAccountNonlocked();
		String isCredentialNonexpired = credential.getIsCredentialNonexpired();
		String isEnabled = credential.getIsEnabled();
		result.setUserId(userId);
		result.setPassword(credential.getAcctPassword());
		result.setAccountNonExpired(BooleanUtils.toBoolean(isAccountNonexpired, "Y", "N"));
		result.setAccountNonLocked(BooleanUtils.toBoolean(isAccountNonlocked, "Y", "N"));
		result.setCredentialNonExpired(BooleanUtils.toBoolean(isCredentialNonexpired, "Y", "N"));
		result.setEnabled(BooleanUtils.toBoolean(isEnabled, "Y", "N"));
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Authority> getGrantedAuthorities(long userId) {
		return authorityMapper.getGrantedAuthoritiesByUserId(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Authority authority) {
		if (authority == null) {
			throw new IllegalArgumentException("authority");
		}
		if (authority.getUserId() == null) {
			throw new IllegalArgumentException("authority userId must be not null");
		}
		this.authorityMapper.remove(authority);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void grant(Authority authority, Long opr) {
		if (opr == null) {
			throw new IllegalArgumentException("userId");
		}
		if (authority == null) {
			throw new IllegalArgumentException("authority");
		}
		this.remove(authority);
		if (CollectionUtils.isEmpty(authority.getRoldIds()))
			return;
		this.grant(authority.getUserId(), authority.getRoldIds(), opr);
	}
}
