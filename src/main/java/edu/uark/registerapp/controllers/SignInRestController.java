package edu.uark.registerapp.controllers;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.ApiResponse;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@RestController
@RequestMapping(value = "/api")
public class SignInRestController extends BaseRestController {
    @RequestMapping(value = "/signOut", method = RequestMethod.DELETE)
    public @ResponseBody ApiResponse removeActiveUser (
        final HttpServletRequest request
    ) {
        //Sign out the user associated with request.getSession().getId() 
        request.getSession().getId();
        
        //remove any record in the activeuser table associated with the current session ID
        final Optional<EmployeeEntity> employeeEntity =
			this.employeeRepository.findByEmployeeId(Integer.parseInt(request.getSession().getId()));
        this.employeeRepository.delete(employeeEntity.get());
        
        return (new ApiResponse()).setRedirectUrl(ViewNames.SIGN_IN.getRoute());
    }
    @Autowired
    private EmployeeRepository employeeRepository;
}