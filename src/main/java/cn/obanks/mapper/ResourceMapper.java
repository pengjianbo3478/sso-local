package cn.obanks.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.model.Resource;
import cn.obanks.model.RoleInfo;

@Repository
public interface ResourceMapper {
	int add(Resource resource);

	int merge(Resource resource);

	RoleInfo load(Long id);

	void remove(Resource resource);
}
