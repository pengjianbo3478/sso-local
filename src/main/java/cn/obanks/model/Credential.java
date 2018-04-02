package cn.obanks.model;
import java.io.Serializable;
import java.util.Date;

public class Credential implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String acctCode;
	private String acctPassword;
	private String acctType;
	private Long userId;
	private String isAccountNonexpired;
	private String isAccountNonlocked;
	private String isCredentialNonexpired;
	private String isEnabled;
	private String remark;
	private Long createdBy;
	private Date createdDate;
	private Long updatedBy;
	private Date updatedDate;
	private String deletedFlag;
	private String keyword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcctCode() {
		return acctCode;
	}

	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}

	public String getAcctPassword() {
		return acctPassword;
	}

	public void setAcctPassword(String acctPassword) {
		this.acctPassword = acctPassword;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIsAccountNonexpired() {
		return isAccountNonexpired;
	}

	public void setIsAccountNonexpired(String isAccountNonexpired) {
		this.isAccountNonexpired = isAccountNonexpired;
	}

	public String getIsAccountNonlocked() {
		return isAccountNonlocked;
	}

	public void setIsAccountNonlocked(String isAccountNonlocked) {
		this.isAccountNonlocked = isAccountNonlocked;
	}

	public String getIsCredentialNonexpired() {
		return isCredentialNonexpired;
	}

	public void setIsCredentialNonexpired(String isCredentialNonexpired) {
		this.isCredentialNonexpired = isCredentialNonexpired;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
