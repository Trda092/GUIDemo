package com.example.week04;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier{

    @GetMapping(value="getChange/{val}")
    public Change getChange(@PathVariable("val") int val){
        int b1000 = (int) Math.floor(val/1000);
        int b500 = (int) Math.floor((val%1000)/500);
        int b100 = (int) Math.floor((val%500)/100);
        int b20 = (int) Math.floor((val%100)/20);
        int b10 = (int) Math.floor((val%20)/10);
        int b5 = (int) Math.floor((val%10)/5);
        int b1 = (int) Math.floor(val%5);
        return new Change(b1000,b500,b100,b20,b10,b5,b1);
    }
}
