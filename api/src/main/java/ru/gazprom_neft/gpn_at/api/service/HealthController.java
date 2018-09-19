package ru.gazprom_neft.gpn_at.api.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Пример контроллера жизнеспособности приложения.
 */
@RestController
@RequestMapping("/monitoring")
public class HealthController {
    private Logger logger = LogManager.getLogger(HealthController.class);

    @Autowired
    ApplicationState applicationState;

    /**
     * Контроль работоспособности приложения.
     * @return - 200 Service is working properly
     * @throws Exception 500 Service unavailable
     */
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    @ResponseBody
    public String health () throws Exception {
        if (applicationState.isNotReady()) {
            throw new Exception("Service unavailable!");
        }
        return new String("Service is working properly");
    }

    @RequestMapping(value = "/liveness", method = RequestMethod.GET)
    @ResponseStatus (HttpStatus.OK)
    public void liveness() {
        logger.info("liveness requested!");
    }

    /**
     * Сломать приложение, гетом, чтобы можно было дернуть из браузера
     * @return Appliacation is broken
     */
    @RequestMapping(value = "/brokeApp", method = RequestMethod.GET)
    @ResponseBody
    public String brokeApp (){
        errorOccured();
        return new String("Appliacation is broken");
    }

    /**
     * Восстановить приложение, гетом, чтобы прямо из браузера
     * @return Application restored
     */
    @RequestMapping(value = "/restoreApp", method = RequestMethod.GET)
    @ResponseBody
    public String restoreApp (){
        applicationState.setNotReady(false);
        return new String("Application restored");
    }

    private void errorOccured(){
        applicationState.setNotReady(true);
    }

}
