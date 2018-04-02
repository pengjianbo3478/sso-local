package cn.obanks.service.impl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.enums.DeletedFlag;
import cn.obanks.mapper.ResourceMapper;
import cn.obanks.model.Resource;
import cn.obanks.model.RoleInfo;
import cn.obanks.service.ResourceService;

@Service(value = "resourceService")
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	protected ResourceMapper resourceMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Resource add(Resource resource) {
		if (resource == null) throw new IllegalArgumentException("resource");
		long createBy = resource.getCreatedBy();
		Date now = new Date();
		resource.setCreatedDate(now);
		resource.setUpdatedBy(createBy);
		resource.setUpdatedDate(now);
		resource.setDeletedFlag(DeletedFlag.NORMAL.getValue());
		this.resourceMapper.add(resource);
		return resource;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Resource merge(Resource resource) {
		if (resource == null) throw new IllegalArgumentException("resource");
		Date now = new Date();
		resource.setUpdatedDate(now);
		this.resourceMapper.merge(resource);
		return resource;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public RoleInfo load(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id");
		}
		return this.resourceMapper.load(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Resource resource) {
		if (resource == null) throw new IllegalArgumentException("roleInfo");
		Date now = new Date();
		resource.setUpdatedDate(now);
		resource.setDeletedFlag(DeletedFlag.DELETED.getValue());
		this.resourceMapper.remove(resource);
	}
}
