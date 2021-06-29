package chaoking.java.allinone.learn.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BeanConfig {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public Producer producer(@Value("${appCode}") String appCode,
                            @Autowired DataSource dataSource){
        SpringTransactionProvider transactionProvider = new SpringTransactionProvider(dataSource);

        System.out.println(appCode);
        System.out.println(dataSource);

        ProducerProvider producer = new ProducerProvider();
        producer.setTransactionProvider(transactionProvider);

        return producer;
    }
}
