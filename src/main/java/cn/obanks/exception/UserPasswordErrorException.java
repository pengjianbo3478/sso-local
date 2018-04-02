package cn.obanks.exception;
public class UserPasswordErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String OLD_PWD_ERROR_MSG = "原密码不正确";

	public UserPasswordErrorException() {
		super();
	}

	public UserPasswordErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserPasswordErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserPasswordErrorException(String message) {
		super(message);
	}

	public UserPasswordErrorException(Throwable cause) {
		super(cause);
	}
}
