package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Пример контроллера жизнеспособности приложения.
 */
@RestController
@RequestMapping("/monitoring")
public class HealthController {

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
