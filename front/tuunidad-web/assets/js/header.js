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
                    alert("Usuario logueado con exito!");
                } else {
                    alert("Usuario no encontrado!");
                }
            };

            var ifErrorLogin = function(data) {
                alert("Al hacer login se genero un error");
            };

            callApi(url, method, request, ifSuccessLogin, ifErrorLogin);
        } 

    });

});