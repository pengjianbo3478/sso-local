package cn.obanks.mapper;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.obanks.common.Paginate;
import cn.obanks.model.UserInfo;

@Repository
public interface UserInfoMapper {
	int add(UserInfo userInfo);

	UserInfo load(Long id);
	
	List<UserInfo> search(Paginate paginate);

	long count(Paginate paginate);
}
