package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import org.springframework.ui.ModelMap;

import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;
import edu.uark.registerapp.controllers.SignInRestController;
import edu.uark.registerapp.models.enums.EmployeeClassification;
import edu.uark.registerapp.commands.employees.EmployeeQuery;
import edu.uark.registerapp.commands.employees.ActiveEmployeeExistsQuery;


@Controller
@RequestMapping(value = "/employeeDetail")
public class EmployeeDetailRouteController extends BaseRouteController {
	String employeeId, password;
    EmployeeSignIn emp;
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView start(
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {
		/*boolean activeUserExists;
		try {
			this.activeEmployeeExistsQuery.execute(); // Query if an active user exists
			activeUserExists = true;
		} catch (final NotFoundException e) {
			activeUserExists = false;
		}
		if (activeUserExists) { // If a current user
			final Optional<ActiveUserEntity> activeUserEntity =
				this.getCurrentUser(request);
			if (!activeUserEntity.isPresent()) {
				return this.buildInvalidSessionResponse();
			} else if (!this.isElevatedUser(activeUserEntity.get())) { // Check if current user is elevated
				return this.buildNoPermissionsResponse();
			}
		}*/
		// TODO: Logic to determine if the user associated with the current session
		//  is able to create an employee
		//make use of functionality built in Task 5
		employeeId = queryParameters.get("ID");
		password = queryParameters.get("password");
		emp = new EmployeeSignIn(employeeId, password);
		final ModelMap model = new ModelMap(ViewNames.EMPLOYEE_DETAIL.getViewName(), queryParameters);

		// check if employees exist
		// If no employees exist Should immediately redirect[4] to the Employee Detail
		// view/document
		if (employeeId != null && (!employeeRepository.existsByEmployeeId(Integer.parseInt(employeeId)) || 
		employeeClassification.isElevatedUser(employeeClassification.getClassification()))) {
			final ModelAndView mv = new ModelAndView(ViewNames.EMPLOYEE_DETAIL.getViewName());
			mv.addObject("employee", new Employee());
			return mv;
		}
		
		else if(!employee.getIsActive()) {
			redirectWithUsingRedirectPrefix(model);
		}
		else{
			redirectToMain(model);
		}
		return null;

	}
	
    

	@GetMapping("/redirecttomain")
    public ModelAndView redirectToMain(final ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/MainMenu", model);
    }
	@GetMapping("/redirectWithRedirectPrefix")
	public ModelAndView redirectWithUsingRedirectPrefix(final ModelMap model) {
        model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/EmployeeDetail", model);
    }

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public ModelAndView startWithEmployee(
		@PathVariable final UUID employeeId,
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {
		final ModelMap model = new ModelMap(ViewNames.EMPLOYEE_DETAIL.getViewName(), queryParameters);

		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);

		if (!activeUserEntity.isPresent()) {
			final ModelAndView mv = new ModelAndView(ViewNames.EMPLOYEE_DETAIL.getViewName());
			mv.addObject("employee", new Employee());
			return mv;
		} else if (!this.isElevatedUser(activeUserEntity.get())) {
			redirectToMain(model);
			return this.buildNoPermissionsResponse();
		}
		else{
			employeeQuery.execute();
			redirectWithUsingRedirectPrefix(model);
		}
		// TODO: Query the employee details using the request route parameter
		// TODO: Serve up the page
		return new ModelAndView(ViewModelNames.EMPLOYEE_TYPES.getValue());
	}
	@Autowired
	EmployeeRepository employeeRepository;
	EmployeeQuery employeeQuery;
	Employee employee;
	EmployeeClassification employeeClassification;
 	ActiveEmployeeExistsQuery active;
	// Helper methods
	private boolean activeUserExists() {
		// TODO: Helper method to determine if any active users Exist
		if ( employeeRepository.existsByIsActive(employee.getIsActive())){
			return true;
		}
		else {
			return false;
		}
	}
}
