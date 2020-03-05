document.addEventListener("DOMContentLoaded", () => {
    var errorMessage = "function has not been implemented ";
    document.getElementById("st").addEventListener("click", function() 
    {
        displayError(errorMessage);
    });

    document.getElementById("2").addEventListener("click", function()
    {
        displayError(errorMessage);
    });

    document.getElementById("3").addEventListener("click", function() 
    {
        displayError(errorMessage)
    });
    document.getElementById("4").addEventListener("click", signOutActionClickHandler());
});