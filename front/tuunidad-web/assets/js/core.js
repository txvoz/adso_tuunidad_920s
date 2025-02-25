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