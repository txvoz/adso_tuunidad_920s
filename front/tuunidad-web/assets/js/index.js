var currentPage = null;
var defaultPage = "inicio";

$(function(){
    redirectByLoginUser(false);
    loadHeader();
    loadFooter();
    getPage(defaultPage);
});

