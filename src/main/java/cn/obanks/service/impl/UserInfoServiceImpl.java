package cn.obanks.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.common.Paginate;
import cn.obanks.mapper.UserInfoMapper;
import cn.obanks.model.UserInfo;
import cn.obanks.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Paginate search(UserInfo userInfo, Paginate paginate) {
		if (paginate == null)
			throw new IllegalArgumentException("paginate");
		paginate.setCondition(userInfo);
		long count = userInfoMapper.count(paginate);
		if (count == 0)
			return paginate;
		paginate.setTotal(count);
		paginate.setData(userInfoMapper.search(paginate));
		return paginate;
	}

}
