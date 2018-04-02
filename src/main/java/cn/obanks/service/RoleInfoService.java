package cn.obanks.service;
import java.util.List;

import cn.obanks.model.RoleInfo;

public interface RoleInfoService {
	RoleInfo add(RoleInfo roleInfo);

	RoleInfo merge(RoleInfo roleInfo);

	RoleInfo load(Long id);

	void remove(RoleInfo roleInfo);
	
	List<RoleInfo> searchAll();

}
