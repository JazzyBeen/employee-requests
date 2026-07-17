package infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ru.ptmk.requestsystem.infrastructure.persistence.repository")
public class JpaConfig {
}
