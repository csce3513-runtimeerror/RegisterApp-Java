package edu.uark.registerapp.models.api;
public class Employee extends ApiResponse {
	private boolean isInitialEmployee;
	public boolean getIsInitialEmployee() {
		return this.isInitialEmployee;
	}
	public ApiResponse setIsInitialEmployee(final boolean isInitialEmployee) {
		this.isInitialEmployee = isInitialEmployee;
		return this;
	}
	public Employee() {
		super();
		this.isInitialEmployee = false;
	}
}