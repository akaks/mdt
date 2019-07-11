var teamId;
var type;  // 类型 区分 新增、编辑、审核等

$(function(){

    teamId = getQueryVariable("teamId");
    type = getQueryVariable("type");

    $("#teamId").val(teamId);

    if(teamId != undefined && teamId != null){
        initData(teamId);
        initData2(teamId);
    }

    if(type != undefined && type != null){
        if (type == 'edit') {

            $("#btn1").show();
        }
        if (type == 'audit') {

            $("#btn2").show();
        }

    }

    // var user = getUser();
    // // 申请人
    // $('#proposer').textbox('setText', user.name);
});

//保存
function save() {

    //判断：编辑表单的所有控件是否都通过验证
    var isValidate= $('#editForm').form('validate');
    if(isValidate==false){
        return ;
    }

    var formdata=getFormData('editForm');

    $.ajax({
        url: baseUrl + '/mdtTeam/saveTeamObjective',
        data:formdata,
        dataType:'json',
        type:'post',
        success:function(value){

            if(value.type == 'success'){
                var mylay = parent.layer.getFrameIndex(window.name);
                parent.layer.close(mylay);

                window.parent.doSearch();
            }
            $.messager.alert('提示',value.message);
        }
    });
}

/**
 * 编辑
 */
function initData(teamId){
    $.ajax({
        url: baseUrl + '/mdtTeam/getTeamObjective?teamId='+teamId,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type == 'success'){
                if (value.resultData.row != null) {
                    $('#editForm').form('load', value.resultData.row);
                }
            }
        }
    });
}

/**
 * 编辑2
 */
function initData2(teamId){
    $.ajax({
        url: baseUrl + '/mdtTeam/get?id='+teamId,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type == 'success'){
                setDate(new Date(value.resultData.row.date));
            }
        }
    });
}

// 从申请日期的下个月开始，填写12个月的月份
function setDate(date) {
    var month = date.getMonth()+1;
    for (var i = 1; i <= 12 ; i++) {
        if (month == 12) {
            month = 0;
        }
        $('#m' + i).textbox('setText', month + 1 + "月");
        month++;
    }
}

function auditSave() {


    $.ajax({
        url: baseUrl + '/mdtTeam/auditAnnualAssess?teamId=' + teamId ,
        dataType:'json',
        success:function(value){
            if(value.type == 'success'){
                var mylay = parent.layer.getFrameIndex(window.name);
                parent.layer.close(mylay);

                window.parent.doSearch();
            }
        }
    });
}