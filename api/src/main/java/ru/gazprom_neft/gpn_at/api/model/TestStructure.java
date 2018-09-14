package ru.gazprom_neft.gpn_at.api.model;

/**
 * Параметры вызова тестов.
 * Нужно будет добавить JSON на вход.
 */
public class TestStructure {
    private Integer threadPoolSize;
    private String test1_url;

    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public String getTest1_url() {
        return test1_url;
    }

    public void setTest1_url(String test1_url) {
        this.test1_url = test1_url;
    }
}
