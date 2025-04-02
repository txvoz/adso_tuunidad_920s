
$(function(){

    var objUser = redirectByLoginUser(true);
    var fullNombre = objUser.nombre;
    $("#data-user").html(fullNombre);
    
    $("#btnLogout").on('click', function(){
       localStorage.clear();
       redirectByLoginUser(true);
    });

    $("#main-nav .nav-link.page, #main-nav .dropdown-item.page").click(function(){
        localStorage.removeItem("id-inmueble");

        var pag = $(this).data('tag');
        if(pag && pag !== null && pag !== undefined) {
            currentPage = pag;
        }
        else {
            currentPage = defaultPage;
        }
        $("#main-nav .nav-link.page, #main-nav .dropdown-item.page").removeClass('active');
        getPage(currentPage, rootPath);
    });

});