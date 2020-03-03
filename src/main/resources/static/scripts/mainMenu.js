document.addEventListener("DOMContentLoaded", () => {
    var error = "function has not been implemented ";
    document.getElementById("st").addEventListener("click", function() 
    {
        displayError(error);
    });

    document.getElementById("2").addEventListener("click", function()
    {
        displayError(error);
    });

    document.getElementById("3").addEventListener("click", function() 
    {
        displayError(error)
    });
    document.getElementById("4").addEventListener("click", signOutActionClickHandler());
});