package edu.uark.registerapp.commands.employees;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.employees.helpers.EmployeeHelper;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

public class EmployeeSignInCommand implements VoidCommandInterface {
    //properties
    private EmployeeSignIn signIn = new EmployeeSignIn();
    private String sessionKey;

    @Transactional
    @Override
    public void execute() {
        final Optional<EmployeeEntity> employeeEntity = this.employeeRepository.
            findByEmployeeId(Integer.parseInt(signIn.getEmployeeId()));
        if(employeeEntity.isPresent()){
            //verify the password
            final byte[] correctP = employeeEntity.get().getPassword();
            byte[] currentP = EmployeeHelper.hashPassword(signIn.getPassword());
            if(correctP.equals(currentP)){
                
            }
            else {

            }
        }
        else{
            throw new NotFoundException("Employee");
        }
    }


    @Autowired
	private EmployeeRepository employeeRepository;
}