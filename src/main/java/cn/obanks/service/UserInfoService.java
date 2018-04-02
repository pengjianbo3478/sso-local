package cn.obanks.service;

import cn.obanks.common.Paginate;
import cn.obanks.model.UserInfo;


public interface UserInfoService {
	
	Paginate search(UserInfo userInfo, Paginate paginate);

}
