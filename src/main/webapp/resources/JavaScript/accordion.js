(function ($) {
    $.fn.accordion = function () {
        $(this).toggleClass('in').next().slideToggle();
        this.not(this).removeClass('in').next().slideUp();
    }
})(jQuery);

$(document).ready(function () {
    $('.panel-heading').click(function(){
        $(this).accordion();
    })
});
