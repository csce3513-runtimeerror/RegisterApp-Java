let hideEmployeeSavedAlertTimer = undefined;

document.addEventListener("DOMContentLoaded", () => {
    // TODO: Things that need doing when the view is loaded
    document.getElementById("save").addEventListener("click", saveActionClick);
    document.getElementById("signOutImage").addEventListener("click", signOutActionClickHandler());
});

// Save
function saveActionClick(event) {
    if (!validateInput()) {
        return;
    }
    const saveActionElement = event.target;
    saveActionElement.disabled = true;
    var empId = document.getElementById("employeeId").value
    const requestPayload = {
        firstName: document.getElementById("firstName").value,
        lastName: document.getElementById("lastName").value,
        password: document.getElementById("password").value,
        empType: document.getElementById("empType").value
    };
    const employeeDetailIdIsDefined = ((empId != null) && (empId.trim() !== ""));
    if (!employeeDetailIdIsDefined) {
        ajaxPost("/employeeDetail", requestPayload, (callbackResponse) => {
            if (isSuccessResponse(callbackResponse)) {
				displayProductSavedAlertModal();
			}
        });
    }
    else {
        ajaxPatch("/employeeDetail", requestPayload, (callbackResponse) => {
            if (isSuccessResponse(callbackResponse)) {
				displayProductSavedAlertModal();
			}
        });
    }
    document.getElementById("employeeId").style.visibility="visible";
	// TODO: Actually save the employee via an AJAX call
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

function displayEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer) {
		clearTimeout(hideEmployeeSavedAlertTimer);
	}

	const savedAlertModalElement = getSavedAlertModalElement();
	savedAlertModalElement.style.display = "none";
	savedAlertModalElement.style.display = "block";

	hideEmployeeSavedAlertTimer = setTimeout(hideEmployeeSavedAlertModal, 1200);
}

function hideEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer) {
		clearTimeout(hideEmployeeSavedAlertTimer);
	}

	getSavedAlertModalElement().style.display = "none";
}
// End save

//Getters and setters
function getSavedAlertModalElement() {
	return document.getElementById("employeeSavedAlertModal");
}