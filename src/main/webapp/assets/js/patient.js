
var method="";//保存提交的方法名称 
$(function(){

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'patientType',title:'患者类型',width:100,formatter:function(value,row,index) {
        	if (row.patientType == '1') {
        		return "门诊";
			} else if (row.patientType == '2') {
        		return "住院";
			}
            return "";
        }},
        {field:'name',title:'姓名',width:100},
        {field:'birthday',title:'出生日期',width:100},
        {field:'gender',title:'性别',width:100,formatter:function(value,row,index) {
            if (row.patientType == '1') {
                return "男";
            } else if (row.patientType == '2') {
                return "女";
            }
            return "";
        }},
        {field:'idcard',title:'身份证号',width:200},

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
		url:baseUrl + '/patient/list'+listParam,
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

                $("tr[id^='outpatient']").each(function () {
                    $(this).show();
                });
                $("tr[id^='hospital']").each(function () {
                    $(this).hide();
                });

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
			url: baseUrl + '/patient/save',
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
                url: baseUrl + '/patient/delete?id='+id,
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

    $.ajax({
        url: baseUrl + '/patient/get?id='+id,
        dataType:'json',
        type:'post',
        success:function(value){

            if(value.type = 'success'){
                $('#editForm').form('load', value.resultData.row);

                changePatientType(value.resultData.row.patientType);

                $('#editWindow').window('open');
            }
        }
    });

	// $('#editForm').form('load', '/user/get?id='+id);
}

function changePatientType(val) {

    var patientType;
    if (val) {
        patientType = val;
	} else {
        patientType = $("input[name='patientType']:checked").val();
	}


    if (patientType == '1') {

        $("tr[id^='outpatient']").each(function () {
            $(this).show();
        });

        $("tr[id^='hospital']").each(function () {
            $(this).hide();
        });

	} else {

        $("tr[id^='outpatient']").each(function () {
            $(this).hide();
        });

        $("tr[id^='hospital']").each(function () {
            $(this).show();
        });
	}
}