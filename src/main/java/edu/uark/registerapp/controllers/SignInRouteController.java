package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import edu.uark.registerapp.commands.employees.EmployeeSignInCommand;
import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Controller
@RequestMapping(value = "/")
public class SignInRouteController extends BaseRouteController {
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
            // redirt to employeeDetail
            return new ModelAndView("redirect:employeeDetail");
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView performSignIn(EmployeeSignIn signIn, HttpServletRequest request) {
        String idtemp = request.getParameter("Id");
        String passtemp = request.getParameter("Password");
        HttpSession id = request.getSession();
        
        try {
            employeeRepository.existsById(UUID.fromString(idtemp));
            employeeSignInCommand.execute();
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
    private EmployeeQuery employeeQuery;
    private ActiveEmployeeExistsQuery activeEmployeeExists;
    private EmployeeRepository employeeRepository;
    private EmployeeSignInCommand employeeSignInCommand;
}