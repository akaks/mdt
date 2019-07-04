
$(function(){

    var url = window.location.href;
    id = url.split("id=")[1];
    if(id != undefined && id != null){
        edit(id);
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
        {field:'title',title:'职称',width:200},
        {field:'phone',title:'联系方式',width:200},
        {field:'phoneCornet',title:'手机短号',width:200},
        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='delApplyDoctor("+row.id+")'>删除</a>";
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
        rownumbers:true,
        toolbar: [{
            iconCls: 'icon-add',
            text:'添加专家',
            handler: function(){

                if (!$("#id").val()) {
                    $.messager.alert('提示', '请先保存MDT申请');
                    return;
                }
                layer.open({
                    type: 2,
                    title: '添加专家',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['80%' , '80%'],
                    content: 'mdtApplyDoctorEdit.html?applyId=' + id
                });
            }
        }]

    });
}


//保存
function save(){

    //判断：编辑表单的所有控件是否都通过验证
    var isValidate= $('#editForm').form('validate');
    if(isValidate==false){
        return ;
    }

    var formdata=getFormData('editForm');

    $.ajax({
        url: baseUrl + '/mdtApply/save',
        data:formdata,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type = 'success'){
                var mylay = parent.layer.getFrameIndex(window.name);
                parent.layer.close(mylay);

                window.parent.doSearch();
            } else {

                $.messager.alert('提示',value.message);
            }
        }
    });
}

/**
 * 删除MDT拟请专家
 */
function delApplyDoctor(id){
	
	$.messager.confirm('提示','确定要删除此记录吗？',function(r){
		if(r)
		{
			$.ajax({
                url: baseUrl + '/mdtApply/delApplyDoctorById?id='+id,
				dataType:'json',
				success:function(value){
                    if(value.type = 'success'){
                        doSearch();
					} else {
                        $.messager.alert('提示',value.message);
                    }
				}
			});		
		}	
	});	
}

/**
 * 编辑
 */
function edit(id){
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

function changeDiseaseName() {
    if (!$("#id").val()) {
        $.messager.alert('提示', '请先保存MDT申请');
        return;
    }

    layer.open({
        type: 2,
        title: 'MDT团队库',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyTeam.html?applyId=' + id
    });
}

function doSearch() {
    $('#grid1').datagrid('reload');
}