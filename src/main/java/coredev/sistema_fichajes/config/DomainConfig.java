package coredev.sistema_fichajes.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("coredev.sistema_fichajes.domain")
@EnableJpaRepositories("coredev.sistema_fichajes.repos")
@EnableTransactionManagement
public class DomainConfig {
}
