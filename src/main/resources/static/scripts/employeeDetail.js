document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("save").addEventListener("click", saveEmployee);
    document.getElementById("signOutImage").addEventListener("click", signOut);
});
function saveEmployee() {
    if (!validateInput()) {
        return;
    }
    document.getElementById("saved").style.display="block";
    const requestPayload = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        password: document.getElementById("password").value,
        empType: document.getElementById("empType").value
    };
    ajaxPost("/employeeDetail", requestPayload, (callbackResponse) => {
    });
    document.getElementById("employeeId").style.display="block";
}
function validateInput() {
    if (document.getElementById("firstName").value == "") {
        displayError("Error: Invalid First Name");
        return false;
    }
    else if (document.getElementById("lastName").value == "") {
        displayError("Error: Invalid Last Name");
        return false;
    }
    else if (document.getElementById("password").value == "" || document.getElementById("confirmPassword").value !=
     document.getElementById("password").value) {
        displayError("Error: Invalid Password");
         return false;
     }
     else if (document.getElementById("empType").value != "Cashier" && 
     document.getElementById("empType").value != "Shift Manager" &&
     document.getElementById("empType").value != "General Manager") {
         displayError("Error: Invalid Employee Type");
         return false;
     }
     else {
         return true;
     }
}
function signOut() {
    window.location.replace("/signIn");
}