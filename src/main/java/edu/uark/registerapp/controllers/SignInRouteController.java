package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;
import edu.uark.registerapp.commands.employees.EmployeeQuery;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Controller
@RequestMapping(value = "/")
public class SignInRouteController extends BaseRouteController{
    private final HttpServletRequest request = null;
    // route for initial page load
    String employeeId, password;
    EmployeeSignIn emp;
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showSignInPage() {
        ModelAndView modelAndView;
        try {
            this.activeEmployeeExists.execute();
            modelAndView = new ModelAndView(ViewNames.SIGN_IN.getViewName());
        } catch (Exception e) {
            //redirt to employeeDetail
            return new ModelAndView("redirect:employeeDetail");
        }
        return modelAndView;
        /*
        ModelAndView modelAndView = new ModelAndView(ViewNames.SIGN_IN.getViewName());
        try {
            modelAndView.addObject(
                ViewModelNames.EMPLOYEE_TYPES.getValue(),
                this.employeeQuery.execute());
        } catch (final Exception e) {
            modelAndView.addObject ( ViewModelNames.ERROR_MESSAGE.getValue(),
            e.getMessage());
        }
        return modelAndView;

        //return new ModelAndView().redirect.concat() to get to employee detail page
        */
    }

    //how to use this method
    /*
    public String getEmployees(@RequestParam Map<String, String> allParams) {
        //make use of functionality built in Task 5
        employeeId = allParams.get("ID");
        password = allParams.get("password");
        emp = new EmployeeSignIn(employeeId, password);
        ModelMap model= new ModelMap(ViewNames.EMPLOYEE_DETAIL.getViewName(), allParams);
        //check if employees exist
        if(employeeId != null) {
            if(employeeRepository.existsByEmployeeId(Integer.parseInt(employeeId))) {
             redirectWithUsingRedirectPrefix(model);
            }
            performSignIn(emp, request);
        }
        return "Parameters are " + allParams.entrySet();
    }

    @GetMapping("/redirectWithRedirectPrefix")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/employeeDetail", model);
    }*/


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView performSignIn( EmployeeSignIn signIn, HttpServletRequest request ) {
        String idtemp = request.getParameter("Id");
        String passtemp = request.getParameter("Password");
        
        ModelAndView modelAndView =
			new ModelAndView(ViewNames.EMPLOYEE_DETAIL.getViewName());
        
        try {
            modelAndView.addObject(ViewModelNames.EMPLOYEE_DETAIL.getValue(),
                this.employeeQuery.setEmployeeId(UUID.fromString(idtemp)).execute());
        }
        catch (Exception e) {
            throw new NotFoundException("Employee");
        }
        //redirect to the main menu if valid credentials
        return new ModelAndView(
            REDIRECT_PREPEND.concat(
                ViewNames.MAIN_MENU.getRoute()));
    }
    @Autowired
    private EmployeeRepository employeeRepository;
    private EmployeeQuery employeeQuery;
    private ActiveEmployeeExistsQuery activeEmployeeExists;
}