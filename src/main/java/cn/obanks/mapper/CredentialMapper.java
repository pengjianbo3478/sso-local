package cn.obanks.mapper;

import org.springframework.stereotype.Repository;
import cn.obanks.model.Credential;

@Repository
public interface CredentialMapper {
	int add(Credential credential);

	Credential load(Long id);

	int remove(Credential credential);

	Credential getByAcctCode(String acctCode);

	int authenticate(Credential credential);

	int selectByAcctCode(Credential credential);

	int merge(Credential credential);

	int changePassword(Credential credential);

	Credential searchCredentialByUserId(Credential credential);

	int countCredentialByUserId(Credential credential);

}
