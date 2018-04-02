package cn.obanks.security.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import cn.obanks.exception.CaptchaException;
import com.google.code.kaptcha.Constants;

public class OBCaptchaAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger LOG = LoggerFactory.getLogger(OBCaptchaAuthenticationFilter.class);
	public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "j_captcha";
	public static final String KAPTCHA_SESSION_KEY = Constants.KAPTCHA_SESSION_KEY;
	private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String genCode = this.obtainGeneratedCaptcha(request);
		String inputCode = this.obtainCaptcha(request);
		LOG.debug("genCode:{},inputCode:{}", genCode, inputCode);
		if (!StringUtils.equals(genCode, inputCode)) {
			throw new CaptchaException(CaptchaException.KAPTCHA_ERROR_KEY);
		}
		return super.attemptAuthentication(request, response);
	}

	protected String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(this.captchaParameter);
	}

	protected String obtainGeneratedCaptcha(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
	}
}
