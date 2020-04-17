package com.ijson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping(value = "/mobile", method = RequestMethod.GET)
    public ModelAndView mobile() {
        ModelAndView view = new ModelAndView("/mobile/login.btl");
        view.addObject("total", 2222);
        return view;
    }
}
