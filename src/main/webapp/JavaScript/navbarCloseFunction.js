(function ($) {
    $.fn.close = function () {
       // $(this).toggleClass('is-visible').slideToggle();
        this.not(this).removeClass('is-visible').slideUp();
    }
})(jQuery);

$(document).ready(function () {
    $(document).click(function(event) {
        $(".demo-drawer.mdl-layout__drawer").close();
    })
});