var currentPage = null;
var defaultPage = "inicio";
var rootPath = "admin/";

$(function(){

    redirectByLoginUser(true);
    loadHeader(rootPath);
    loadFooter(rootPath);
    getPage(rootPath)

});