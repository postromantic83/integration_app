package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gazprom_neft.gpn_at.api.model.LoadTestResult;
import ru.gazprom_neft.gpn_at.api.model.ResponseData;

/**
 * Основной контроллер тестового сервиса.
 */
@RestController
@RequestMapping("/serviceapi")
public class MainController {

    @Autowired
    private TestingService testingService;

    /**
     * Привет мир.
     * @return
     */
    @RequestMapping (value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello world! Welcome to ESB-KSPD!";
    }

    /**
     * Тестовые данные
     * @return JSON с данными.
     */
    @RequestMapping (value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getData(){
        ResponseData data = new ResponseData();
        data.setLongData(123456L);
        data.setStringData("Данные для возврата JSON объекта в зону DMZ из КСПД");
        return data;
    }

    /**
     * Запуск теста №1
     * @param requests - количество запросов в тесте.
     * @return - результаты выполнения теста (JSON).
     */
    @RequestMapping (value = "/loadTest1/{requests}", method = RequestMethod.GET)
    @ResponseBody
    public LoadTestResult loadTest1 (@PathVariable("requests") Integer requests){
        return testingService.loadTest1(requests);
    }

}
