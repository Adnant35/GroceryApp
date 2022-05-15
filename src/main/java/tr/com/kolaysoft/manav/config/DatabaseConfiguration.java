package tr.com.kolaysoft.manav.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({ "tr.com.kolaysoft.manav.repository" })
@EnableTransactionManagement
public class DatabaseConfiguration {}
