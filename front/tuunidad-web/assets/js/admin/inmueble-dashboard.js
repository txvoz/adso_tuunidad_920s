$(function () {
    cargarZonas();
});

function cargarZonas() {
    var ifSuccessGetDashboard = function(response){
        $("#zonas").html("");
        for(var i = 0; i < response.data.length; i++){
            var item = response.data[i];
            
            var html = "<div class='zona'>";

            html += "<h4>"+capitalizeAndPluralizeText(item.tipo)+"</h4>";
            html += "<div class='elementos'>";

            for(var j = 0; j < item.inmuebles.length; j++){
                var inmueble = item.inmuebles[j];
                html += "<div class='elemento "+item.tipo.toLowerCase()+"'>";
                html += "<label>"+inmueble.nomenclatura+"</label>";
                html += "<div class='icono'></div>";
                html += "</div>";
            }

            html += "</div>";
            html += "</div>";

            $("#zonas").append(html);
        }    
        closeLoader();
    }

    var url = "http://localhost:8787/inmueble/dashboard";
    openLoader();
    callApi(url, "GET", null, ifSuccessGetDashboard, ifErrorRequest);
}