function getCssProperty(elmId, property) {
    var elem = document.getElementById(elmId);
    return window.getComputedStyle(elem, null).getPropertyValue(property);
}

$(document).ready(function () {

    var beginning_opacity = 1;
    var scroll_pos = 0;
    var animation_begin_pos = 0; //where you want the animation to begin
    var animation_end_pos = 1000; //where you want the animation to stop
    var beginning_color = new $.Color("rgb(210,247,212)"); //we can set this here, but it'd probably be better to get it from the CSS; for the example we're setting it here.
    var ending_color = new $.Color("rgb(255,248,199)");
    //what color we want to use in the end
    $(document).scroll(function () {
        scroll_pos = $(this).scrollTop();
        if ((scroll_pos >= animation_begin_pos) && (scroll_pos <= animation_end_pos)) {

            var percentScrolled = scroll_pos / (animation_end_pos - animation_begin_pos);
            var newRed = beginning_color.red() + ((ending_color.red() - beginning_color.red()) * percentScrolled);
            var newGreen = beginning_color.green() + ((ending_color.green() - beginning_color.green()) * percentScrolled);
            var newBlue = beginning_color.blue() + ((ending_color.blue() - beginning_color.blue()) * percentScrolled);
            var newColor = new $.Color(newRed, newGreen, newBlue);

            var newOpacity;

            $("body").animate({backgroundColor: newColor}, 0);
        } else if (scroll_pos > animation_end_pos) {
            $("body").animate({backgroundColor: ending_color}, 0);
        } else if (scroll_pos < animation_begin_pos) {
            $("body").animate({backgroundColor: beginning_color}, 0);
        }

        

    });
});