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

function loadHeader(root){
    root = root===null || root === undefined? "": root;
    var url = 'template/'+root+'header.html';
    var idContent = 'content-header';
    loadZone(url, idContent);
}

function loadFooter(root){
    root = root===null || root === undefined? "": root;
    var url = 'template/'+root+'footer.html';
    var idContent = 'content-footer';
    loadZone(url, idContent);
}

function loadPage(page, root) {
    root = root===null || root === undefined ? "": root;
    var url = 'template/'+root+'pages/'+page+'.html';
    var idContent = 'content-main';
    loadZone(url, idContent);
}

function getPage(currentPage, root){
    currentPage = currentPage === null ? defaultPage : currentPage;
    loadPage(currentPage,root);
    $("#btn-"+currentPage).addClass('active');
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


function openLoader(){
    $("#xmask").addClass('show');
}

function closeLoader(){
    $("#xmask").removeClass('show');
}

function addAlert(msg, type, time = null){
    if(time <= 0) {
        time = 5;
    }

    var id = "alert_" + getRandomInt(1000, 99999);

    var html = '<div id="'+id+'" class="alert alert-'+type+'" role="alert" style="display:none">';
    html += msg;
    html += "</div>"

    $("#alerts").prepend($(html));
    $("#"+id).show('fast');

    time = time===null ? 5000 : time * 1000;

    window.setTimeout(function(){
        $("#"+id).hide('fast');
    }, time);
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min; // Inclusive of min and max
  }

function redirectByLoginUser(logged) {
    var dataUser = localStorage.getItem("data-user"); 

    if((dataUser === null || dataUser === undefined || dataUser === "") && logged ) {
        window.location.replace("index.html");
        return;
    }

    if((dataUser !== null) && !logged ) {
        window.location.replace("admin.html");
        return;
    }

    if(logged) {
        var objUser = JSON.parse(dataUser);
        console.log("USUARIO LOG", objUser);
        return objUser;
    }


}

var ifErrorRequest = function(data) {
    addAlert("Se presento un error en el servidor", "danger", 8);
    closeLoader();
};