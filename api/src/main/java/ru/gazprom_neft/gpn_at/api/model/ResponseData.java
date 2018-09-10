package ru.gazprom_neft.gpn_at.api.model;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private String stringData;
    private Long longData;

    public Long getLongData() {
        return longData;
    }

    public void setLongData(Long longData) {
        this.longData = longData;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
