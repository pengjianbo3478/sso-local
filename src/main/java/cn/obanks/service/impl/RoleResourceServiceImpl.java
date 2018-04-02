package cn.obanks.service.impl;

import java.util.Date;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.enums.DeletedFlag;
import cn.obanks.mapper.RoleResourceMapper;
import cn.obanks.model.RoleResource;
import cn.obanks.service.RoleResourceService;

public class RoleResourceServiceImpl implements RoleResourceService {
	private static final Logger LOG = LoggerFactory.getLogger(RoleResourceServiceImpl.class);
	@Resource
	protected RoleResourceMapper roleResourceMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RoleResource add(RoleResource roleResource) {
		if (roleResource == null)
			throw new IllegalArgumentException("roleResource");
		LOG.debug("roleResource: {}", roleResource);
		long createBy = roleResource.getCreatedBy();
		Date now = new Date();
		roleResource.setCreatedDate(now);
		roleResource.setUpdatedBy(createBy);
		roleResource.setUpdatedDate(now);
		roleResource.setDeletedFlag(DeletedFlag.NORMAL.getValue());
		this.roleResourceMapper.add(roleResource);
		return roleResource;
	}
}
