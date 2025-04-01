function resetModal() {
    $("#email, #password").each(function(){
        $(this).removeClass('is-invalid');
        $(this).val('');
    });

    $('#login-form')[0].reset();
}

$(function(){

    $("#email, #password").on('change', function(){
        $(this).removeClass('is-invalid');
    });

    var myModal = document.getElementById('loginModal');
    myModal.addEventListener('hidden.bs.modal', function () {
        resetModal();
    });

    $("#btnLogin").on('click', function(){
        $("#login-form button[type=submit]").click();
    });

    $("#main-nav .nav-link.page, #main-nav .dropdown-item.page").click(function(){
        var pag = $(this).data('tag');
        if(pag && pag !== null && pag !== undefined) {
            currentPage = pag;
        }
        else {
            currentPage = defaultPage;
        }
        $("#main-nav .nav-link.page, #main-nav .dropdown-item.page").removeClass('active');
        getPage(currentPage);
    });


    $("#login-form").submit(function(event) {
        event.preventDefault();

        var isValidForm = true;
        var email = $("#email").val();
        var password = $("#password").val();

        if(email === "") {
            $("#email").addClass('is-invalid');
            isValidForm = false;
        }

        if(password === "") {
            $("#password").addClass('is-invalid');
            isValidForm = false;
        }

        if(isValidForm) {

            var request = {
                username: $("#email").val(), 
                password: $("#password").val()
            }; 


            console.log("Login::request " + JSON.stringify(request));

            var url = "http://localhost:8787/auth";
            var method = "POST";
            var ifSuccessLogin = function(apiResponse) {
                console.log("Login::response " + JSON.stringify(apiResponse));
                if(apiResponse.data.active) {
                    addAlert("Usuario logueado con exito", "success",3);
                    window.setTimeout(function(){
                        var dataUser = JSON.stringify(apiResponse.data);
                        localStorage.setItem("data-user", dataUser);
                        window.location.replace("admin.html?")
                    }, 2000);
                } else {
                    $('#login-form')[0].reset();
                    addAlert("Usuario no encontrado", "warning");
                }
                closeLoader();
            };

            var ifErrorLogin = function(data) {
                addAlert("Se presento un error en el servidor", "danger", 8);
                closeLoader();
            };

            openLoader();
            callApi(url, method, request, ifSuccessLogin, ifErrorLogin);
        } 

    });

});