package cn.obanks.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.obanks.enums.DeletedFlag;
import cn.obanks.mapper.RoleInfoMapper;
import cn.obanks.model.RoleInfo;
import cn.obanks.service.RoleInfoService;

@Service
public class RoleInfoServiceImpl implements RoleInfoService {
	@Resource
	protected RoleInfoMapper roleInfoMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RoleInfo add(RoleInfo roleInfo) {
		if (roleInfo == null)
			throw new IllegalArgumentException("roleInfo");
		long createBy = roleInfo.getCreatedBy();
		Date now = new Date();
		roleInfo.setCreatedDate(now);
		roleInfo.setUpdatedBy(createBy);
		roleInfo.setUpdatedDate(now);
		roleInfo.setDeletedFlag(DeletedFlag.NORMAL.getValue());
		this.roleInfoMapper.add(roleInfo);
		return roleInfo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RoleInfo merge(RoleInfo roleInfo) {
		if (roleInfo == null)
			throw new IllegalArgumentException("roleInfo");
		Date now = new Date();
		roleInfo.setUpdatedDate(now);
		this.roleInfoMapper.merge(roleInfo);
		return roleInfo;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RoleInfo load(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id");
		}
		return this.roleInfoMapper.load(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(RoleInfo roleInfo) {
		if (roleInfo == null)
			throw new IllegalArgumentException("roleInfo");
		Date now = new Date();
		roleInfo.setUpdatedDate(now);
		roleInfo.setDeletedFlag(DeletedFlag.DELETED.getValue());
		this.roleInfoMapper.remove(roleInfo);
	}

	@Override
	public List<RoleInfo> searchAll() {
		return this.roleInfoMapper.searchAll();
	}

}
