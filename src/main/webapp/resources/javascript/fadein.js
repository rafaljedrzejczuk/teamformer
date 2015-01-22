$(document).ready(function () {
    $("#top").css({opacity: 0});
    $("#content").css({opacity: 0});
    $("#bottom").css({opacity: 0});

    setTimeout(function () {
        $("#top").fadeTo(500, 1);
        $("#content").fadeTo("slow", 1);
        $("#bottom").fadeTo(3000, 1);
    }, 500);
});