package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.gazprom_neft.gpn_at.api.model.TestStructure;

/**
 * Конфигурация тестового окружения.
 */
@Configuration
@PropertySource("classpath:web.properties")
public class TestConfig {
    @Value("${test1.URL}")
    private String TEST1_URL;

    @Value("${test.thread_pull}")
    private Integer THREAD_POOL;

    @Bean
    public TestStructure getTestStructure(){
        TestStructure structure = new TestStructure();
        structure.setThreadPoolSize(this.THREAD_POOL);
        structure.setTest1_url(this.TEST1_URL);
        return structure;
    }

}
