package cn.obanks.service.impl;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.obanks.mapper.CredentialMapper;
import cn.obanks.model.Credential;
import cn.obanks.service.AuthenticateService;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {
	@Autowired
	private CredentialMapper credentialMapper;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean authenticate(String acctCode, String acctPassword) {
		Credential credential = new Credential();
		credential.setAcctCode(acctCode);
		credential.setAcctPassword(acctPassword);
		int i = this.credentialMapper.authenticate(credential);
		Boolean result = BooleanUtils.toBooleanObject(i);
		return result;
	}
//	
//	public static void main(String[] args) {
//		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
//		String result = md5PasswordEncoder.encodePassword("123456", null);
//		System.out.println(result);
//	}
}
