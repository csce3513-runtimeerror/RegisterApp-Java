package edu.uark.registerapp.models.api; 

public class EmployeeSignIn {
    private String employeeId;
    private String password;

    public EmployeeSignIn(String employeeId, String password){
        this.employeeId = employeeId;
        this.password = password;
    }
    public EmployeeSignIn(){
        super();
    }

    public String getEmployeeId(){
        return employeeId;
    }
    public String getPassword() {
        return password;
    }
    public void setEmployeeId(String id) {
        employeeId = id;
    }
    public void setPassword(String pass) {
        password = pass;
    }
}