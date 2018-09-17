package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitoring")
public class HealthController {



    @RequestMapping(value = "/health")
    @ResponseBody
    public String health (){
        return new String("Приложение исправно");
    }

}
