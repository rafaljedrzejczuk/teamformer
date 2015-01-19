$(document).ready(function () {
    
    $("#top").hide();
    $("#content").hide();
    $("#bottom").hide();

    setTimeout(function () {
        $("#top").fadeIn();
        $("#content").fadeIn("slow");
        $("#bottom").fadeIn(3000);
    }, 500);
});