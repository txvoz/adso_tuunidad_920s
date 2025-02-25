var currentPage = null;
var defaultPage = "inicio";

$(function(){
    loadHeader();
    loadFooter();
    getPage();
});

function getPage(){
    currentPage = currentPage === null ? defaultPage : currentPage;
    loadPage(currentPage);
    $("#btn-"+currentPage).addClass('active');
}