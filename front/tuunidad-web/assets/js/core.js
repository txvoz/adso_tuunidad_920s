var validMethods = ["GET", "POST", "PUT", "DELETE"];

function loadZone(pathOrigin, idElement) {
    const numeroAleatorio = Math.random(); 
    $.ajax({
        url: pathOrigin + "?n=" + numeroAleatorio,
        type: "GET",
        success: function (result) {
            $("#"+idElement).html(result);
        },
        error: function (xhr, status, error) {
        }
    });

}

function loadHeader(){
    var url = 'template/header.html';
    var idContent = 'content-header';
    loadZone(url, idContent);
}

function loadFooter(){
    var url = 'template/footer.html';
    var idContent = 'content-footer';
    loadZone(url, idContent);
}

function loadPage(page) {
    var url = 'template/pages/'+page+'.html';
    var idContent = 'content-main';
    loadZone(url, idContent);
}

function callApi(url, method, data, cbSuccess, cbError) {

    console.log("callApi :: " + method + " :: " + url);


    isPresent = validMethods.find(function(item){
        return item === method;
    });

    if(isPresent === "") {
        alert("Metodo " + method + "No permitido");
        return;
    }

    var jsonData = "";
    if(method === "POST" || method === "PUT") {
        jsonData = JSON.stringify(data);
    }

    $.ajax({
        url: url,
        type: method,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: jsonData, 
        headers: {
            'Authorization':'token123'
        },
        success: function (result) {
            try {
                cbSuccess(result);
            } catch (e) {
                console.log("Error en cbSuccess", e);
            }
        },
        error: function (xhr, status, error) {
            try {
                console.log(error);
                cbError(xhr.status);
            } catch (e) {
                cbErrorBase(xhr.status);
                console.log("Error en cbError", e);
            }
        }
    });
}

function cbErrorBase(error) {
    alert("El llamado al servidor fallo " + error);
}