package cn.obanks.mapper;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.obanks.model.RoleInfo;
import cn.obanks.model.RoleResource;

@Repository
public interface RoleInfoMapper {
	int add(RoleInfo roleInfo);

	int merge(RoleInfo roleInfo);

	RoleInfo load(Long id);

	void remove(RoleInfo roleInfo);

	List<RoleResource> getRoleResource(String appId);

	List<RoleInfo> getGrantedAuthoritiesByAcctCode(String acctCode);

	RoleInfo searchEx01(String roleCode);

	List<RoleInfo> searchAll();
}
