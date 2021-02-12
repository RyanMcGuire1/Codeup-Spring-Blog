package codeupspring.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{x}/and/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int x, @PathVariable int y){
        return  x + " + " + y + " = " + (x + y);
    }

    @RequestMapping(path = "/subtract/{x}/from/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int x, @PathVariable int y){
        return y + " - " + x + " = " + (y - x);
    }

    @RequestMapping(path = "/multiply/{x}/and/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y) {
        return x + " * " + y + " = " + (x * y);
    }

    @RequestMapping(path = "/divide/{x}/by/{y}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int x, @PathVariable int y){
        return x + " / " + y + " = " + (x / y);
    }


}
