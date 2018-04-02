package cn.obanks.exception;
import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	public static final String KAPTCHA_ERROR_KEY = "captcha.error";

	public CaptchaException(String msg, Throwable t) {
		super(msg, t);
	}

	public CaptchaException(String msg) {
		super(msg);
	}
}
