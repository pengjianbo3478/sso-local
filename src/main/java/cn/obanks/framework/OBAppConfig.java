package cn.obanks.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@ComponentScan({ "cn.obanks" })
@Configuration
@EnableWebMvc
@PropertySource("classpath:obanks.properties")
@ImportResource("classpath:obanks.xml")
public class OBAppConfig {

	@Bean
	public TilesViewResolver getTilesViewResolver() {
		return new TilesViewResolver();
	}

	@Bean
	public TilesConfigurer getTilesConfigurer() {
		final TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles/obanks.cn.xml" });
		return tilesConfigurer;
	}

	@Bean
	public SimpleMappingExceptionResolver getExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		resolver.setDefaultErrorView("error");
		resolver.setDefaultStatusCode(500);
		resolver.setWarnLogCategory(resolver.getClass().getName());
		resolver = new SimpleMappingExceptionResolver();
		resolver.setDefaultErrorView("error");
		resolver.setDefaultStatusCode(404);
		resolver.setWarnLogCategory(resolver.getClass().getName());
		return resolver;
	}

}
