
$(function(){

    var url = window.location.href;
    id = url.split("id=")[1];
    if(id != undefined && id != null){
        edit(id);
    }

    initGrid1(id)
});

function initGrid1(teamId) {

    var columns=[[
		/*{field:'id',title:'编号',width:100},*/
        {field:'name',title:'专家姓名',width:100},
        {field:'department',title:'科室',width:200},
        {field:'title',title:'职称',width:200},
        {field:'phone',title:'联系方式',width:200},
        {field:'specialistType',title:'专家类型',width:200,formatter:function(value,row,index) {
            if (row.specialistType == '1') {
                return "首席专家";
            } else if (row.specialistType == '2') {
                return "团队副组长";
            } else if (row.specialistType == '3') {
                return "团队秘书";
            } else if (row.specialistType == '4') {
                return "专家";
            }
            return '';
        }},
        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='editTeamInfo("+row.id+")'>编辑</a>&nbsp;&nbsp;&nbsp;" +
                "<a href='#' onclick='delTeamInfo("+row.id+")'>删除</a>";
        }}
    ]];

    //表格数据初始化
    $('#grid1').datagrid({
        title:'MDT团队基本信息（多人明细）',
        url:baseUrl + '/mdtTeam/findTeamInfoByTeamId?teamId=' + teamId,
        loadFilter: function(data){
            return data.resultData;
        },
        columns:columns,
        singleSelect:true,
        rownumbers:true,
        toolbar: [{
            iconCls: 'icon-add',
            text:'添加专家',
            handler: function(){
                addTeamInfo(id)
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
        url: baseUrl + '/mdtTeam/save',
        data:formdata,
        dataType:'json',
        type:'post',
        success:function(value){

            if(value.type = 'success'){
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
function edit(id){
	method="update";
	$('#editWindow').window('open');

    $.ajax({
        url: baseUrl + '/mdtTeam/get?id='+id,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type = 'success'){
                $('#editForm').form('load', value.resultData.row);
            }
        }
    });
}

function addTeamInfo(teamId) {
    if (!teamId) {
        $.messager.alert('提示', '请先保存MDT团队申请');
        return;
    }

    layer.open({
        type: 2,
        title: 'MDT团队基本信息（多人明细）',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtTeamInfoEdit.html?teamId=' + teamId
    });
}

function editTeamInfo(id) {
    layer.open({
        type: 2,
        title: 'MDT团队基本信息（多人明细）',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtTeamInfoEdit.html?id=' + id
    });
}

// 删除 MDT团队基本信息（多人明细）
function delTeamInfo(id) {

    $.messager.confirm('提示','确定要删除此记录吗？',function(r){
        if(r)
        {
            $.ajax({
                url: baseUrl + '/mdtTeam/delTeamInfo?id='+id,
                dataType:'json',
                success:function(value){
                    if(value.type = 'success'){
                        doSearch();
                    }
                }
            });
        }
    });
}

function doSearch() {
    $('#grid1').datagrid('reload');
}