var teamId;
var type;  // 类型 区分 新增、编辑、审核等

$(function(){

    teamId = getQueryVariable("teamId");
    type = getQueryVariable("type");

    $("#teamId").val(teamId);

    if(teamId != undefined && teamId != null){
        initData(teamId);

        initGrid1(teamId);

        initGrid2(teamId);
    }


    if(type != undefined && type != null){
        if (type == 'edit') {

            $("#btn1").show();
        }
        if (type == 'audit') {

            $("#btn2").show();
            $("#btn3").show();
        }

    }

});


function initGrid1(teamId) {

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'title',title:'论文题目',width:100},
        {field:'serialNumber',title:'期刊号',width:200},
        {field:'postedDate',title:'发表时间',width:200},
        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='delTeamPaper("+row.id+")'>删除</a>";
        }}
    ]];

    //表格数据初始化
    $('#grid1').datagrid({
        title:'建期两年MDT病种研究方向发表的论文',
        url:baseUrl + '/mdtTeam/selectTeamPaper?teamId=' + teamId,
        loadFilter: function(data){
            return data.resultData;
        },
        columns:columns,
        singleSelect:true,
        rownumbers:true,
        toolbar: [{
            iconCls: 'icon-add',
            text:'添加',
            handler: function(){

                layer.open({
                    type: 2,
                    title: '添加',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['80%' , '80%'],
                    content: 'mdtTeamPaperEdit.html?teamId=' + teamId
                });
            }
        }]

    });
}


function initGrid2(teamId) {

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'name',title:'项目名称',width:100},
        {field:'researchDate',title:'项目研究时间',width:200},
        {field:'projectFund',title:'项目经费',width:200},
        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='delTeamIssue("+row.id+")'>删除</a>";
        }}
    ]];

    //表格数据初始化
    $('#grid2').datagrid({
        title:'建期两年MDT病种研究方向开展的课题探究',
        url:baseUrl + '/mdtTeam/selectTeamIssue?teamId=' + teamId,
        loadFilter: function(data){
            return data.resultData;
        },
        columns:columns,
        singleSelect:true,
        rownumbers:true,
        toolbar: [{
            iconCls: 'icon-add',
            text:'添加',
            handler: function(){

                layer.open({
                    type: 2,
                    title: '添加',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['80%' , '80%'],
                    content: 'mdtTeamIssueEdit.html?teamId=' + teamId
                });
            }
        }]

    });
}

//保存
function save() {

    //判断：编辑表单的所有控件是否都通过验证
    var isValidate= $('#editForm').form('validate');
    if(isValidate==false){
        return ;
    }

    var formdata=getFormData('editForm');

    $.ajax({
        url: baseUrl + '/mdtTeam/saveTeamAssess',
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
        url: baseUrl + '/mdtTeam/getTeamAssess?teamId='+teamId,
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

function delTeamPaper(id) {
    $.messager.confirm('提示','确定要删除此记录吗？',function(r){
        if(r)
        {
            $.ajax({
                url: baseUrl + '/mdtTeam/delTeamPaper?id='+id,
                dataType:'json',
                success:function(value){
                    if(value.type == 'success'){
                        doSearch();
                    }
                }
            });
        }
    });
}

function delTeamIssue(id) {
    $.messager.confirm('提示','确定要删除此记录吗？',function(r){
        if(r)
        {
            $.ajax({
                url: baseUrl + '/mdtTeam/delTeamIssue?id='+id,
                dataType:'json',
                success:function(value){
                    if(value.type == 'success'){
                        doSearch();
                    }
                }
            });
        }
    });
}

function doSearch() {
    $('#grid1').datagrid('load');
    $('#grid2').datagrid('load');
}

function sauditSave(val) {

    $.ajax({
        url: baseUrl + '/mdtTeam/auditTwoYearAssess?teamId=' + teamId + '&result=' + val,
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