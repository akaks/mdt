
$(function(){

    var url = window.location.href;
    id = url.split("id=")[1];
    if(id != undefined && id != null){
        initData(id);
    }

    initGrid1(id);
});

/**
 * 生成 MDT拟请专家列表
 * @param applyId
 */
function initGrid1(applyId) {

    var columns=[[
		/*{field:'id',title:'编号',width:100},*/
        {field:'name',title:'专家名称',width:100},
        {field:'department',title:'科室',width:200},
        {field:'title',title:'职称',width:100},
        {field:'phone',title:'手机长号',width:100},
        {field:'phoneCornet',title:'手机短号',width:100},
        {field:'-',title:'操作',width:100,formatter:function(value,row,index) {
            return "<a href='#' onclick='grade("+row.id+", "+row.userId+")'>打分</a>";
        }}
    ]];

    //表格数据初始化
    $('#grid1').datagrid({
        title:'拟请MDT参加专家',
        url:baseUrl + '/mdtApply/listApplyDoctorByApplyId?applyId=' + applyId,
        loadFilter: function(data){
            return data.resultData;
        },
        columns:columns,
        singleSelect:true,
        rownumbers:true
    });
}

/**
 * 编辑
 */
function initData(id){
    $.ajax({
        url: baseUrl + '/mdtApply/get?id='+id,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type = 'success'){
                $('#editForm').form('load', value.resultData.row);
            }
        }
    });
}

function grade(id, userId) {

    layer.open({
        type: 2,
        title: '打分',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyDeptGradeItem.html?id=' + id + '&userId=' + userId
    });
}

function sendMsg() {

    //判断：编辑表单的所有控件是否都通过验证
    var isValidate= $('#editForm').form('validate');
    if(isValidate==false){
        return ;
    }

    var formdata=getFormData('editForm');

    $.ajax({
        url: baseUrl + '/mdtApply/sendMsg',
        data:formdata,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type = 'success') {

                $.messager.alert('提示', "发送成功");
            } else {

                $.messager.alert('提示',value.message);
            }
        }
    });
}