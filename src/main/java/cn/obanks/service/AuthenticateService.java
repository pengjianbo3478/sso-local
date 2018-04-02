package cn.obanks.service;
public interface AuthenticateService {
	boolean authenticate(String acctName, String password);
}
