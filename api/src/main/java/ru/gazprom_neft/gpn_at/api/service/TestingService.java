package ru.gazprom_neft.gpn_at.api.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.gazprom_neft.gpn_at.api.model.LoadTestResult;
import ru.gazprom_neft.gpn_at.api.model.TestStructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Сервис выполнения тестов.
 */
@Service
public class TestingService {
    static final Logger logger = LogManager.getLogger(TestingService.class);

    private TestStructure testStructure;

    TestingService(TestStructure structure){
        this.testStructure = structure;
    }


    /**
     * Нагрузочный тест.
     * @param load - количество запросов
     * @return - результат выполнения
     */
    public LoadTestResult loadTest1(Integer load){
        logger.info("Старт теста at " + System.currentTimeMillis());
        LoadTestResult testResult = new LoadTestResult();
        AtomicReference<Integer> errors = new AtomicReference<>(0);

        ExecutorService executorService = Executors.newFixedThreadPool(testStructure.getThreadPoolSize());

        Callable<String> callableTask = () -> {
            String result = null;
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(getHandler());
            try {
                result = restTemplate.getForObject(testStructure.getTest1_url(), String.class);
            }catch (RestClientException e){
                logger.error("Ошибка запроса:" + e.getMessage());
                errors.getAndSet(errors.get() + 1);
            }
            return result;
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        for(int i = 0; i<load; i++){
            callableTasks.add(callableTask);
            logger.info("Task added: " + i);
        }

        long startTime = System.currentTimeMillis();
        logger.info("Invoking all  at : " + startTime);
        try {
            List<Future<String>> futures = executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            logger.error("Ошибка исполнителя потоков: " + e.getMessage());
        }

        long endTime = System.currentTimeMillis();
        logger.info("End time at : " + endTime);
        long testDuration = endTime - startTime;
        testResult.setNumberErrors(errors.get());
        testResult.setTestResult("Test execution time:  " + testDuration + "ms") ;
        return testResult;
    }

    private ResponseErrorHandler getHandler(){
        ResponseErrorHandler handler = new ResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
                logger.error("Ошибка при вызове сервиса!");
                throw new IOException("Ошибка при вызове сервиса!" + clientHttpResponse);
            }
        };
        return handler;
    }
}
