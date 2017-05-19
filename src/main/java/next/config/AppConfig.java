package next.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import core.jdbc.ConnectionManager;

@Configuration
@ComponentScan(basePackages = { "next.dao", "next.service",
		"core.jdbc" }, excludeFilters = @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION))
public class AppConfig {
	@Bean
	public JdbcTemplate viewResolver() {
		JdbcTemplate bean = new JdbcTemplate(ConnectionManager.getDataSource());
		return bean;
	}
}
