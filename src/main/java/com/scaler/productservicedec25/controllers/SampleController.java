package com.scaler.productservicedec25.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//this class will be hosting a set of HTTP APIs;
@RestController
@RequestMapping("/say")
public class SampleController {

    @GetMapping("/hello/{name}/{times}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("times") int times){

        String output="";

        for(int i=0; i<times; i++){
            output = output + "hello " + name + " ";
        }
        return output;
    }

    @GetMapping("/bye")
    public String sayBye(){
        return "bye everyone";
    }

}
