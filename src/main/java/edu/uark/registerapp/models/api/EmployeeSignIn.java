package edu.uark.registerapp.models.api; 

public class EmployeeSignIn {
    private String employeeId;
    private String password;

    public EmployeeSignIn(String employeeId, String password){
        this.employeeId = employeeId;
        this.password = password;
    }

    public String getEmployeeId(){
        return employeeId;
    }
    public String getPassword() {
        return password;
    }
    
}