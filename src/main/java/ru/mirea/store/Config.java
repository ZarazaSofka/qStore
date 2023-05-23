package ru.mirea.store;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;


@Configuration
@EnableJpaRepositories
@EnableAspectJAutoProxy
@EnableScheduling
@EnableJdbcHttpSession
public class Config {}