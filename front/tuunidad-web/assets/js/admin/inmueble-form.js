$(function(){
    cargarTipoInmueble();

    $("#formulario-inmueble").on("submit", function(e){
        e.preventDefault();
        
        var request = {
            "nomenclatura": $("#nomenclatura").val(),
            "m2": $("#m2").val(),
            "idTipoInmueble": $("#tipo_inmueble").val(),
        }

        guardarNuevoInmueble(request);

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
    }

    
    var url = "http://localhost:8787/tipo-inmueble";
    openLoader();
    callApi(url, "GET", null, ifSuccessGetTipoInmueble, ifErrorRequest);
}