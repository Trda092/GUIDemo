package com.example.week04;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MathAPI {
    @RequestMapping(value = "/plus/{n1}/{n2}",  method = RequestMethod.GET)
    public double myPlus(@PathVariable("n1") double num1,@PathVariable("n2") double num2){
        return num1+num2;
    }
    @RequestMapping(value = "/minus/{n1}/{n2}",  method = RequestMethod.GET)
    public double myMinus(@PathVariable("n1") double num1,@PathVariable("n2") double num2){
        return num1-num2;
    }
    @RequestMapping(value = "/divide/{n1}/{n2}",  method = RequestMethod.GET)
    public double myDivide(@PathVariable("n1") double num1,@PathVariable("n2") double num2){
        return num1/num2;
    }
    @RequestMapping(value = "/multi/{n1}/{n2}",  method = RequestMethod.GET)
    public double myMulti(@PathVariable("n1") double num1,@PathVariable("n2") double num2){
        return num1*num2;
    }
    @RequestMapping(value = "/mod/{n1}/{n2}",  method = RequestMethod.GET)
    public double myMod(@PathVariable("n1") double num1,@PathVariable("n2") double num2){
        return num1%num2;
    }
    @RequestMapping(value = "/max",  method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public double myMax(@RequestBody MultiValueMap<String, String> n){
        Map<String, String> d = n.toSingleValueMap();
        return Math.max(Double.parseDouble(d.get("num1")), Double.parseDouble(d.get("num2")));
    }
}
