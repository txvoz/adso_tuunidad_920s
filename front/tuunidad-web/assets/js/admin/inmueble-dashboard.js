var dataDashboard = [];
var currentId = null;

$(function () {

    var myModal = document.getElementById('detalleModal');
    myModal.addEventListener('hidden.bs.modal', function () {
        closeModalDetalle();
    });

    $("#addDetalle").click(function(){
        $("#step-1").hide("fast");
        $("#step-2").show("slow");
    });

    cargarZonas();
});


function closeModalDetalle(){
    //$('#detalle-form')[0].reset();
}

function openModalDetalle(){
    $("#detalleModal").modal('show');
}

function cargarZonas() {
    var ifSuccessGetDashboard = function(response){
        dataDashboard = response.data;
        $("#zonas").html("");
        for(var i = 0; i < response.data.length; i++){
            var item = response.data[i];
            
            var html = "<div class='zona'>";

            html += "<h4>"+capitalizeAndPluralizeText(item.tipo)+"</h4>";
            html += "<div class='elementos'>";

            for(var j = 0; j < item.inmuebles.length; j++){
                var inmueble = item.inmuebles[j];
                html += "<div data-index='"+i+"-"+j+"' class='elemento "+item.tipo.toLowerCase()+" "+inmueble.estadoInmueble.toLowerCase()+"'>";
                html += "<label>"+inmueble.nomenclatura+"</label>";
                html += "<div class='icono'>";
                html += "<div class='estado'></div>"
                html += "</div>";
                html += "</div>";
            }

            html += "</div>";
            html += "</div>";

            $("#zonas").append(html);
        }    

        $(".elementos .elemento").click(function(){
            //"0-1".split("-") = ["0", "1"]
            var indices = $(this).data("index").split("-");
            var inmueble = dataDashboard[parseInt(indices[0])].inmuebles[parseInt(indices[1])];

            currentId = inmueble.id;
            $("#nomenclatura").html(inmueble.nomenclatura);
            var html = "<div>Estado: " + inmueble.estadoInmueble +"</div>";
            html += "<div>Detalle: " + inmueble.estadoDetalle+"</div>";
            html += "<div>Propietario: " + inmueble.propietarioPrincipal+"</div>";
            html += "<div>Cantidad propietarios: " + inmueble.propietarios.length+"</div>";
            html += "<div>Cantidad autorizados: " + inmueble.personasAutorizadas.length+"</div>";

            if((inmueble.estadoInmueble == "EN_PROCESO" || inmueble.estadoInmueble == "ACTIVO") && inmueble.estadoDetalle == 'INACTIVO') {
                $("#addDetalle").show()
            }

            $("#detalleModal output").html(html);

            openModalDetalle();
        });

        closeLoader();
    }   

    var url = "http://localhost:8787/inmueble/dashboard";
    openLoader();
    callApi(url, "GET", null, ifSuccessGetDashboard, ifErrorRequest);
}