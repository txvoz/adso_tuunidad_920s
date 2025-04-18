$(function(){
    cargarInmuebles();
});

function cargarInmuebles(){

    var ifSuccessGetInmuebles = function(response){
        $("#inmueble-list tbody").html("");
        for(var i = 0; i < response.data.length; i++){
            var item = response.data[i];
            
            var html = "";
            html += "<tr>";
            html += "<td>"+item.id+"</td>";
            html += "<td>"+item.nomenclatura+"</td>";
            html += "<td>"+item.m2+"</td>";
            html += "<td>"+item.tipo+"</td>";
            html += "<td>"+item.propietario+"</td>";
            html += "<td>" 
            html += "<button data-id='"+item.id+"' type='button' class='delete btn btn-outline-danger' style='margin-right:5px'>Eliminar</button>"
            html += "<button data-id='"+item.id+"' type='button' class='detail btn btn-outline-info'>Editar</button>"
            html += "</td>";
            html += "</tr>";

            $("#inmueble-list tbody").prepend(html);
        }    
        setListenerBtnDeleteInmueble();
        setListenerDetailInmueble();
        closeLoader();
    }

    
    var url = "http://localhost:8787/inmueble";
    openLoader();
    callApi(url, "GET", null, ifSuccessGetInmuebles, ifErrorRequest);
}

function setListenerBtnDeleteInmueble(){
    $("#inmueble-list .delete").on("click", function(){
        var id = $(this).data('id');
        if(confirm("Seguro de que desea eliminar el registro?")) {
            eliminarInmueble(id);
        }
    });
}

function setListenerDetailInmueble(){
    $("#inmueble-list .detail").on("click", function(){
        var id = $(this).data('id');
        detalleInmueble(id);
    });
}

function detalleInmueble(id) {
    localStorage.setItem("id-inmueble", id);
    loadPage("inmueble-form", rootPath, "id="+id);
}

function eliminarInmueble(id) {
    var ifSuccessDeleteInmueble = function(response){
        cargarInmuebles();
        closeLoader();
    }

    var url = "http://localhost:8787/inmueble/"+id;
    openLoader();
    callApi(url, "DELETE", null, ifSuccessDeleteInmueble, ifErrorRequest);
}