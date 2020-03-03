package edu.uark.registerapp.commands.employees;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

public class EmployeeSignInCommand implements VoidCommandInterface {
    //properties
    private EmployeeSignIn signIn = new EmployeeSignIn();
    private String sessionKey;

    @Override
    public void execute() {
        final Optional<EmployeeEntity> employeeEntity = this.employeeRepository.
            findByEmployeeId(Integer.parseInt(signIn.getEmployeeId()));
        if(employeeEntity.isPresent()){
            //verify the password
            
        }
        else{
            throw new NotFoundException("Employee");
        }
    }
    @Autowired
	private EmployeeRepository employeeRepository;
}