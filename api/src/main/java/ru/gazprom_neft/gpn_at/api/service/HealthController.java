package ru.gazprom_neft.gpn_at.api.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Пример контроллера жизнеспособности приложения.
 */
@RestController
@RequestMapping("/monitoring")
@PropertySource("classpath:web.properties")
public class HealthController {
    private Logger logger = LogManager.getLogger(HealthController.class);

    @Autowired
    ApplicationState applicationState;

    @Value("${app.hostname}")
    private String hostName;

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

    @RequestMapping(value = "/ips")
    @ResponseBody
    public String hello() throws UnknownHostException {
        ArrayList<InetAddress> ips = null;
        Integer availableIP = 0;
        ips = new ArrayList<InetAddress>(Arrays.asList(InetAddress.getAllByName(hostName)));

        for(InetAddress inetAddress : ips){
            logger.info(hostName + " ip: " + inetAddress);
            availableIP++;
        }
        return new String("Hello world! Host:" + hostName + " Available IPs: " + availableIP);
    }


    private void errorOccured(){
        applicationState.setNotReady(true);
    }

}
