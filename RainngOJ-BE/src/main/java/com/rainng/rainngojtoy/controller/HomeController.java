package com.rainng.rainngojtoy.controller;

import com.rainng.jerry.mvc.annotation.Route;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends BaseController {

    /**
     * 欢迎使用Spring + JerryFramework (*^▽^*)
     */
    @Route("/")
    public String index() {
        return "Hello SpringBoot + JerryFramework!";
    }
}
