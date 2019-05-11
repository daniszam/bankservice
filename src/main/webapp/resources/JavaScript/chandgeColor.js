$(document).ready(function () {
    var thema = getCookie('thema');
    if (thema=='black') {
        $('body').toggleClass('dark');
    } else {
        $('body').removeClass('dark');
    }
});

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) return parts.pop().split(";").shift();
}