var id="";//保存提交的方法名称

$(function(){

    var url = window.location.href;
    id = url.split("id=")[1];
    if(id != undefined && id != null){
        initData(id);
    }

});
