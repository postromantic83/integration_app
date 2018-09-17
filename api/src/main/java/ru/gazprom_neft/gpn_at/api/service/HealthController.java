package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/monitoring")
public class HealthController {

    @Autowired
    ApplicationState applicationState;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    @ResponseBody
    public String health () throws Exception {
        if (applicationState.isNotReady()) {
            throw new Exception("Service unavailable!");
        }
        return new String("Service is working properly");
    }

    @RequestMapping(value = "/brokeApp", method = RequestMethod.GET)
    @ResponseBody
    public String brokeApp (){
        errorOccured();
        return new String("Appliacation is broken");
    }

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
