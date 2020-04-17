package com.ijson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping("/default/buy")
    public ModelAndView buy() {
        ModelAndView modelAndView = new ModelAndView("/default/buy");
        modelAndView.addObject("total", 10000);
        return modelAndView;
    }

    @RequestMapping("/buy")
    public ModelAndView buy2() {
        ModelAndView modelAndView = new ModelAndView("/buy2");
        modelAndView.addObject("total", 10000);
        return modelAndView;
    }
}
