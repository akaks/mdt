
var method="";//保存提交的方法名称

function initGrid1() {

    var columns=[[
		/*{field:'id',title:'编号',width:100},*/
        {field:'a1',title:'编号',width:100},
        {field:'a2',title:'专家名称',width:100},
        {field:'a3',title:'科室',width:200},
        {field:'a4',title:'职称',width:200},
        {field:'a5',title:'联系方式',width:200},
        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='dele("+row.id+")'>删除</a>";
        }}
    ]];

    //表格数据初始化
    $('#grid1').datagrid({
        title:'拟请MDT参加专家',
        url:baseUrl + '/mdtApply/list4',
        loadFilter: function(data){
            return data.resultData;
        },
        columns:columns,
        singleSelect:true,
        toolbar: [{
            iconCls: 'icon-add',
            text:'添加专家',
            handler: function(){
                // method="add";
                // $('#editWindow').window('open');
                // $('#editForm').form('clear');
            }
        }]

    });
}

$(function(){
    initGrid1();

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'number',title:'患者类型',width:100},
        {field:'name',title:'患者姓名',width:100},
        {field:'name',title:'入院/首诊时间',width:100},
        {field:'name',title:'MDT时间',width:100},
        {field:'name',title:'MDT地点',width:100},
        {field:'name',title:'审核状态',width:100},

        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='edit("+row.id+")'>修改</a> <a href='#' onclick='dele("+row.id+")'>删除</a>";
        }}
    ]];
	
	if(typeof(listParam)=='undefined'){
		listParam='';		
	}
	if(typeof(saveParam)=='undefined'){
		saveParam='';		
	}
	
	//表格数据初始化
	$('#grid').datagrid({
		url:baseUrl + '/mdt/apply'+listParam,
        loadFilter: function(data){
            return data.resultData;
        },
		columns:columns,
		singleSelect:true,
		pagination:true,
		toolbar: [{
			iconCls: 'icon-add',
			text:'增加',
			handler: function(){
				method="add";
				$('#editWindow').window('open');
				$('#editForm').form('clear');
			}
		}]

	});
	
	//条件查询
	$('#btnSearch').bind('click',function(){
		var formdata=getFormData('searchForm');
		$('#grid').datagrid('load',formdata);
	});

	//保存
	$('#btnSave').bind('click',function(){

		//判断：编辑表单的所有控件是否都通过验证
		var isValidate= $('#editForm').form('validate');
		if(isValidate==false){
			return ;
		}

		var formdata=getFormData('editForm');

		$.ajax({
			url: '/user/save',
			data:formdata,
			dataType:'json',
			type:'post',
			success:function(value){

				if(value.type = 'success'){
					$('#editWindow').window('close');
					$('#grid').datagrid('reload');
				}
				$.messager.alert('提示',value.message);
			}

		});

	});

});

/**
 * 删除 
 */
function dele(id){
	
	$.messager.confirm('提示','确定要删除此记录吗？',function(r){
		if(r)
		{
			$.ajax({
				url:name+'_delete.action?id='+id,
				dataType:'json',
				success:function(value){
					if(value.success){
						$('#grid').datagrid('reload');
					}
					$.messager.alert('提示',value.message);
				}
			});		
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
        url: '/user/get?id='+id,
        dataType:'json',
        type:'post',
        success:function(value){

            if(value.type = 'success'){
                $('#editForm').form('load', value.resultData.row);
            }
        }
    });

	// $('#editForm').form('load', '/user/get?id='+id);
}