package cn.obanks.service;
import cn.obanks.exception.UserPasswordErrorException;
import cn.obanks.model.Credential;

public interface CredentialService {
	Credential add(Credential credential, Long opr);

	boolean exists(String acctType, String acctCode);

	void deprecateCredential(long userId);

	Credential merge(Credential credential);

	Credential load(Long id);

	void remove(Credential credential);
	
	void changePassword(String password, Credential credential) throws UserPasswordErrorException;
	
	int changePassword(Credential credential);
}
