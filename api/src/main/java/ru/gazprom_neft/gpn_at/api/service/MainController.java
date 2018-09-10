package ru.gazprom_neft.gpn_at.api.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gazprom_neft.gpn_at.api.model.ResponseData;

@RestController
@RequestMapping("/serviceapi")
public class MainController {

    @RequestMapping (value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello Sasha!";
    }


    @RequestMapping (value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getData(){
        ResponseData data = new ResponseData();
        data.setLongData(123456L);
        data.setStringData("Данные для возврата JSON объекта в зону DMZ из КСПД");
        return data;
    }
}
