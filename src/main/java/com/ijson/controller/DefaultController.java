package com.ijson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @RequestMapping(value = "/params", method = RequestMethod.GET)
    public ModelAndView mobile() {
        ModelAndView view = new ModelAndView("/default/params");
        view.addObject("total", 2222);
        view.addObject("title", "你好,中国!");
        return view;
    }
}
