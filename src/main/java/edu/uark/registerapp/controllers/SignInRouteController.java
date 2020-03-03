package edu.uark.registerapp.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.EmployeeSignIn;

@Controller
@RequestMapping(value = "/")
public class SignInRouteController extends BaseRouteController {
    //route for initial page load
    String employeeId, password;
    @RequestMapping(method = RequestMethod.GET)
    public String getEmployees(@RequestParam Map<String, String> allParams) {
        //make use of functionality built in Task 5
        employeeId = allParams.get("ID");
        password = allParams.get("password");

        ModelMap model= new ModelMap(ViewNames.EMPLOYEE_DETAIL.getViewName(), allParams);
        //check if employees exist
        if(employeeId == "") {
            redirectWithUsingRedirectPrefix(model);
        }

        return "Parameters are " + allParams.entrySet();
    }

    @GetMapping("/redirectWithRedirectPrefix")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/EmployeeDetail", model);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView performSignIn( 
        EmployeeSignIn signIn,
        HttpServletRequest request
    ) {
        //use the credentials provided in the request body and the "id" property
        //of the (HttpServletRequest)request.getsession() variable 
        //to sign in the user
        String id = (request.getSession()).getId();
        

        return new ModelAndView(
            REDIRECT_PREPEND.concat(
                ViewNames.MAIN_MENU.getRoute()));
    }
}