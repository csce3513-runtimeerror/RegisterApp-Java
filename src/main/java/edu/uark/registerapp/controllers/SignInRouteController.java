package edu.uark.registerapp.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.EmployeeSignIn;

@Controller
@RequestMapping(value = "/")
public class SignInRouteController {
    //route for initial page load
    String employeeId, password;
    @RequestMapping(method = RequestMethod.GET)
    public String getEmployees(@RequestParam Map<String, String> allParams) {
        //make use of functionality built in Task 5
        employeeId = allParams.get("ID");
        password = allParams.get("password");

        //check if employees exist
        //incomplete

        return "Parameters are " + allParams.entrySet();
    }



    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FROM_URLENCODED_VALUE)
    public ModelAndView performSignIn(
        //define an object that will represent the sign in request and add it as a parameter 

        HttpServletRequest request
    ) {
        //use the credentials provided in the request body and the "id" property
        //of the (HttpServletRequest)request.getsession() variable 
        //to sign in the user

        return new ModelAndView(
            REDIRECT_PREPEND.concat(
                ViewNames.MAIN_MENU.getRoute()));
    }
}