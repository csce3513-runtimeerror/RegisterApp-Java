package edu.uark.registerapp.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value =  "/mainMenu")
public class MainMenuRouteController {
    @RequestMapping(value= "/mainMenu", method = RequestMethod.GET)
    public void start(@RequestParam Map<String,String> query, HttpServletRequest req) {

    }
}