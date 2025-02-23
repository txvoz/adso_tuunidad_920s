var currentPage = null;

$(function(){
    loadHeader();
    loadFooter();
    getPage();
});

function getPage(){
    currentPage = currentPage === null ? 'pag2' : currentPage;
    loadPage(currentPage);
    $("#btn-"+currentPage).addClass('active');
}