package cn.obanks.mapper;
import org.springframework.stereotype.Repository;
import cn.obanks.model.RoleResource;

@Repository
public interface RoleResourceMapper {
	int remove(RoleResource roleResource);

	int add(RoleResource roleResource);
}
