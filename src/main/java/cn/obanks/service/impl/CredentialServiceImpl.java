package cn.obanks.service.impl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.obanks.enums.AcctType;
import cn.obanks.enums.DeletedFlag;
import cn.obanks.exception.UserPasswordErrorException;
import cn.obanks.mapper.CredentialMapper;
import cn.obanks.model.Authority;
import cn.obanks.model.Credential;
import cn.obanks.service.AuthorityService;
import cn.obanks.service.CredentialService;

@Service
public class CredentialServiceImpl implements CredentialService {
	@Autowired
	private CredentialMapper credentialMapper;
	@Autowired
	private AuthorityService authorityService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Credential add(Credential credential, Long opr) {
		final String acctPassword = credential.getAcctPassword();
		credential.setAcctPassword(Md5PasswordEncoder.encode(acctPassword));
		credential.setIsAccountNonexpired("Y");
		credential.setIsAccountNonlocked("Y");
		credential.setIsCredentialNonexpired("Y");
		credential.setIsEnabled("Y");
		credential.setCreatedBy(opr);
		Date now = new Date();
		Long createdBy = credential.getCreatedBy();
		credential.setCreatedDate(now);
		credential.setUpdatedBy(createdBy);
		credential.setUpdatedDate(now);
		if (null == credential.getAcctType()) {
			credential.setAcctType(AcctType.CPM.getValue());
		}
		credential.setDeletedFlag(DeletedFlag.NORMAL.getValue());
		this.credentialMapper.add(credential);
		credential.setAcctPassword(acctPassword);
		return credential;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean exists(String acctType, String acctCode) {
		Credential credential = new Credential();
		credential.setAcctType(acctType);
		credential.setAcctCode(acctCode);
		int i = this.credentialMapper.selectByAcctCode(credential);
		return BooleanUtils.toBooleanObject(i);
	}

	@Override
	public void deprecateCredential(long userId) {
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Credential merge(Credential credential) {
		if (credential == null) throw new IllegalArgumentException("credential");
		Date now = new Date();
		credential.setUpdatedDate(now);
		this.credentialMapper.merge(credential);
		return credential;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Credential load(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("id");
		}
		return this.credentialMapper.load(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Credential credential) {
		if (credential == null) throw new IllegalArgumentException("credential");
		if (credential.getUserId() == null) {
			throw new IllegalArgumentException("credential.userId is mandatory");
		}
		this.credentialMapper.remove(credential);
		Authority authority = new Authority();
		Long userId = credential.getUserId();
		authority.setUserId(userId);
		this.authorityService.remove(authority);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void changePassword(String password, Credential credential) throws UserPasswordErrorException {
		if (password == null) {
			throw new IllegalArgumentException("password");
		}
		if (credential == null) {
			throw new IllegalArgumentException("credential");
		}
		if (credential.getUserId() == null) {
			throw new IllegalArgumentException("credential.userId is mandatory");
		}
		if (null == credential.getAcctType()) {
			credential.setAcctType(AcctType.CPM.getValue());
		}
		String newPassword=credential.getAcctPassword();
		credential.setAcctPassword(Md5PasswordEncoder.encode(password));
		int exists = this.credentialMapper.countCredentialByUserId(credential);
//		String oldPassword = cr.getAcctPassword();
//		if (!StringUtils.equals(oldPassword, Md5PasswordEncoder.encode(password))) {
//			throw new UserPasswordErrorException();
//		}
		if (0 >= exists) {
			throw new UserPasswordErrorException();
		}
		credential.setAcctPassword(newPassword);
		if (null == credential.getUpdatedBy()) {
			credential.setUpdatedBy(credential.getUserId());
		}
		this.changePassword(credential);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int changePassword(Credential credential) {
		if (credential == null) {
			throw new IllegalArgumentException("credential");
		}
		if (credential.getUserId() == null) {
			throw new IllegalArgumentException("credential.userId is mandatory");
		}
		credential.setUpdatedDate(new Date());
		credential.setAcctPassword(Md5PasswordEncoder.encode(credential.getAcctPassword()));
		if (null == credential.getAcctType()) {
			credential.setAcctType(AcctType.CPM.getValue());
		}
		return this.credentialMapper.changePassword(credential);
	}
}

final class Md5PasswordEncoder {
	private static final Logger LOG = LoggerFactory.getLogger(Md5PasswordEncoder.class);
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	static final String encode(String source) {
		if (StringUtils.isNotBlank(source)) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update(source.getBytes());
				byte[] digest = messageDigest.digest();
				final StringBuilder result = new StringBuilder(digest.length * 2);
				for (int i = 0; i < digest.length; i++) {
					result.append(HEX_DIGITS[(digest[i] >> 4) & 0x0f]);
					result.append(HEX_DIGITS[digest[i] & 0x0f]);
				}
				return result.toString();
			}
			catch (NoSuchAlgorithmException e) {
				LOG.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}
		}
		throw new IllegalArgumentException();
	}
}
