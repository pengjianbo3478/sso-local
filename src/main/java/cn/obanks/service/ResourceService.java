package cn.obanks.service;
import cn.obanks.model.Resource;
import cn.obanks.model.RoleInfo;

public interface ResourceService {
	Resource add(Resource resource);

	Resource merge(Resource resource);

	RoleInfo load(Long id);

	void remove(Resource resource);
}
