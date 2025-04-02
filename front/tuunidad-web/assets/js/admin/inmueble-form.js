var prevId = null;
var inmueble = null;
$(function(){

    cargarTipoInmueble();

    $("#formulario-inmueble").on("submit", function(e){
        e.preventDefault();
        
        var request = {
            "nomenclatura": $("#nomenclatura").val(),
            "m2": $("#m2").val(),
            "idTipoInmueble": $("#tipo_inmueble").val(),
        }

        if(prevId === null) {
            guardarNuevoInmueble(request);
        } else {
            editarInmueble(request);
        }
        

    });

});

function guardarNuevoInmueble(request) {

    var ifSuccessGetInmueble = function(response){
        $("#formulario-inmueble")[0].reset();
        closeLoader();
        addAlert("Inmueble registrado con exito", "success",3);
    }
    
    var url = "http://localhost:8787/inmueble";
    openLoader();
    callApi(url, "POST", request, ifSuccessGetInmueble, ifErrorRequest);

}

function editarInmueble(request) {

    var ifSuccessGetInmueble = function(response){
        $("#formulario-inmueble")[0].reset();
        closeLoader();
        addAlert("Inmueble registrado con exito", "success",3);
        localStorage.removeItem("id-inmueble");
    }
    
    var url = "http://localhost:8787/inmueble/"+prevId;
    openLoader();
    callApi(url, "PUT", request, ifSuccessGetInmueble, ifErrorRequest);

}


function validarTipoFormulario(){
    prevId = localStorage.getItem("id-inmueble");
    if(prevId === null) {
        $("#title-new-inmueble").removeClass('hidden');
    } else {
        cargarDataInmueble();
    }
}

function cargarDataInmueble(){
    var ifSuccessGetInmueble = function(response){
        $("#title-edit-inmueble label").html(response.data.nomenclatura);
        $("#title-edit-inmueble").removeClass('hidden');

        $("#nomenclatura").val(response.data.nomenclatura);
        $("#m2").val(response.data.m2);
        $("#tipo_inmueble").val(parseInt(response.data.idTipo)).change();
        
        closeLoader();
    }

    
    var url = "http://localhost:8787/inmueble/"+prevId;
    openLoader();
    callApi(url, "GET", null, ifSuccessGetInmueble, ifErrorRequest);
}

function cargarTipoInmueble(){

    var ifSuccessGetTipoInmueble = function(response){
        for(var i = 0; i < response.data.length; i++){
            var item = response.data[i];
            var itemHtml = $("<option>");
            $(itemHtml).html(item.titulo);
            $(itemHtml).attr("value", item.id);
            $("#tipo_inmueble").append($(itemHtml));
        }    
        closeLoader();
        validarTipoFormulario();
    }

    
    var url = "http://localhost:8787/tipo-inmueble";
    openLoader();
    callApi(url, "GET", null, ifSuccessGetTipoInmueble, ifErrorRequest);
}