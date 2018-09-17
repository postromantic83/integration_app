package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.stereotype.Component;

/**
 * Компонент для хранения статуса приложения.
 */

@Component
public class ApplicationState {
    private boolean isNotReady;

    public boolean isNotReady() {
        return isNotReady;
    }

    public void setNotReady(boolean notReady) {
        isNotReady = notReady;
    }
}
