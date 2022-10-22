package com.example.doormanagement.jdbi;

import com.example.doormanagement.chipcard.EntityChipCard;
import com.example.doormanagement.gateway.EntityGateway;
import com.example.doormanagement.passage.EntityPassage;
import com.example.doormanagement.person.EntityPerson;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapperFactory;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource driverManagerDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        return Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin())
                .registerRowMapper(RowMapperFactory.of(EntityChipCard.class, new EntityRowMapper<>(EntityChipCard.class)))
                .registerRowMapper(RowMapperFactory.of(EntityGateway.class, new EntityRowMapper<>(EntityGateway.class)))
                .registerRowMapper(RowMapperFactory.of(EntityPassage.class, new EntityRowMapper<>(EntityPassage.class)))
                .registerRowMapper(RowMapperFactory.of(EntityPerson.class, new EntityRowMapper<>(EntityPerson.class)))
                ;
    }

}
