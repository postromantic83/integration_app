package ru.gazprom_neft.gpn_at.api.model;

import java.io.Serializable;

/**
 * Результат выполнения теста.
 */
public class LoadTestResult implements Serializable {

    private String testResult;
    private Integer numberErrors;

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public Integer getNumberErrors() {
        return numberErrors;
    }

    public void setNumberErrors(Integer numberErrors) {
        this.numberErrors = numberErrors;
    }

    @Override
    public String toString() {
        return "LoadTestResult{" +
                "testResult='" + testResult + '\'' +
                ", numberErrors=" + numberErrors +
                '}';
    }
}
