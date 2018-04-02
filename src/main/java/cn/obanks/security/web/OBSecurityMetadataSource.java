package cn.obanks.security.web;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import cn.obanks.model.RoleResource;
import cn.obanks.service.AuthorityService;

public class OBSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private static final Logger LOG = LoggerFactory.getLogger(OBSecurityMetadataSource.class);
	private final String appId;
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	@Autowired(required = true)
	private AuthorityService authorityService;

	public OBSecurityMetadataSource(final String appId) {
		this.appId = appId;
		LOG.info("create FtiaoFilterInvocationSecurityMetadataSource with appId " + appId);
	}

	private void init() {
		LOG.debug("MetadataSource initialing... " + appId);
		List<RoleResource> authorities = authorityService.getRoleResource(appId);
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		if ((null != authorities) && (!authorities.isEmpty())) {
			for (RoleResource authority : authorities) {
				final String url = authority.getUrl();
				final String role = authority.getRoleCode();
				LOG.debug("Authority url:" + url + ", role:" + role);
				final ConfigAttribute ca = new SecurityConfig(role);
				if (resourceMap.containsKey(url)) {
					resourceMap.get(url).add(ca);
				}
				else {
					final Collection<ConfigAttribute> attr = new ArrayList<ConfigAttribute>();
					attr.add(ca);
					resourceMap.put(url, attr);
				}
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		final Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>();
		if ((null != resourceMap) && (!resourceMap.isEmpty())) {
			for (String key : resourceMap.keySet()) {
				final Collection<ConfigAttribute> attributes = resourceMap.get(key);
				result.addAll(attributes);
			}
			LOG.debug("getAllConfigAttributes return ConfigAttribute size:" + result.size());
		}
		return result;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(final Object key) throws IllegalArgumentException {
		LOG.debug("getAttributes " + key);
		final FilterInvocation filterInvocation = (FilterInvocation) key;
		final String url = filterInvocation.getRequestUrl();
		LOG.debug("url: " + url);
		final HttpServletRequest request = filterInvocation.getRequest();
		Collection<ConfigAttribute> result = null;
		if (null != getResourceMap()) {
			for (final String pattern : getResourceMap().keySet()) {
				if (new RegexRequestMatcher(pattern, null, true).matches(request)) {
					result = getResourceMap().get(pattern);
					LOG.debug("return " + result);
					return result;
				}
			}
		}
		return result;
	}

	@Override
	public boolean supports(final Class<?> arg0) {
		return true;
	}

	private Map<String, Collection<ConfigAttribute>> getResourceMap() {
		if (null == resourceMap) {
			init();
		}
		return resourceMap;
	}
}
