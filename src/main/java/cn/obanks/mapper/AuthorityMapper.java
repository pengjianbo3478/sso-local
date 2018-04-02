package cn.obanks.mapper;
import java.util.List;
import org.springframework.stereotype.Repository;
import cn.obanks.model.Authority;

@Repository
public interface AuthorityMapper {
	int add(Authority record);

	List<Authority> getGrantedAuthoritiesByUserId(long userId);

	int remove(Authority authority);
}
