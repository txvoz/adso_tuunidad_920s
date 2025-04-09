
$(function(){

    $("#formulario-persona").on("submit", function(e){
        e.preventDefault();
        
        var request = {
            "nombre": $("#nombre").val(),
            "apellido": $("#apellido").val(),
            "genero": $("#genero").val(),
            "fechaNacimiento": $("#fecha-nacimiento").val(),
            "tipoIdentificacion": $("#tipo-identificacion").val(),
            "identificacion": $("#identificacion").val(),
            "telefono": $("#telefono").val(),
            "correo": $("#correo").val(),
            "idMunicipio": $("#municipio").val(),
            "direccion": $("#direccion").val(),
            "zip": $("#zip").val(),
        }

        guardarNuevaPersona(request);
        
    });

});

function guardarNuevaPersona(request) {

    var ifSuccessNuevaPersona = function(response){
        $("#formulario-persona")[0].reset();
        closeLoader();
        addAlert("Registro creado con exito", "success",3);
    }
    
    var url = "http://localhost:8787/persona";
    openLoader();
    callApi(url, "POST", request, ifSuccessNuevaPersona, ifErrorRequest);

}
