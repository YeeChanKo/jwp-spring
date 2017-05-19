package next.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "next.dao", "next.service",
		"core.jdbc" }, excludeFilters = @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION))
@PropertySource("classpath:application.properties")
public class AppConfig {

	@Value("${db.driver}")
	private String DB_DRIVER;
	@Value("${db.url}")
	private String DB_URL;
	@Value("${db.username}")
	private String DB_USERNAME;
	@Value("${db.password}")
	private String DB_PW;

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate bean = new JdbcTemplate(dataSource);
		return bean;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DB_DRIVER);
		ds.setUrl(DB_URL);
		ds.setUsername(DB_USERNAME);
		ds.setPassword(DB_PW);
		return ds;
	}

	// @Value를 사용하기 위한 설정
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
