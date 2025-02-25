$(function(){
    $("#main-nav .nav-link.page").click(function(){
        var pag = $(this).data('tag');
        if(pag && pag !== null && pag !== undefined) {
            currentPage = pag;
        }
        else {
            currentPage = defaultPage;
        }
        $("#main-nav .nav-link.page").removeClass('active');
        getPage();
    });


    $("#login-form").submit(function(event) {
        event.preventDefault();

        var email = $("#email").val();
        var password = $("#password").val();

        alert(email + " - " + password);

    });

});