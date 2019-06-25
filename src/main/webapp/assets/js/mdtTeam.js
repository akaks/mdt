
$(function(){

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'proposer',title:'申请人',width:100},
        {field:'name',title:'MDT名称',width:200},
        {field:'date',title:'申请日期',width:100},
        {field:'auditStatus',title:'审核状态',width:100,formatter:function(value,row,index) {
            // 0:未审核 1:科主任审核 2:医务部主任审核 3:分管院长审核
            if (row.auditStatus == '0') {
                return "未审核";
            } else if (row.auditStatus == '1') {
                return "科主任已审核";
            } else if (row.auditStatus == '2') {
                return "医务部主任已审核";
            } else if (row.auditStatus == '3') {
                return "分管院长已审核";
            }
            return '';
        }},
        {field:'-',title:'操作',width:200,formatter:function(value,row,index) {
            return "<a href='#' onclick='edit("+row.id+")'>修改</a> " +
                "<a href='#' onclick='audit("+row.id+")'>审核</a> " +
                "<a href='#' onclick='dele("+row.id+")'>删除</a>";
        }}
    ]];
	
	//表格数据初始化
	$('#grid').datagrid({
		url:baseUrl + '/mdtTeam/findByPage',
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

                layer.open({
                    type: 2,
                    title: 'MDT团队',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['80%' , '80%'],
                    content: 'mdtTeamEdit.html'
                });

			}
		}]

	});
	
	//条件查询
	$('#btnSearch').bind('click',function(){
		var formdata=getFormData('searchForm');
		$('#grid').datagrid('load',formdata);
	});

});

/**
 * 删除 
 */
function audit(id){
	
	$.messager.confirm('提示','确定要审核此记录吗？',function(r){
		if(r)
		{
            $.messager.alert('提示', '');
			// $.ajax({
             //    url: baseUrl + '/mdtTeam/delete?id='+id,
			// 	dataType:'json',
			// 	success:function(value){
			// 		if(value.success){
			// 			$('#grid').datagrid('reload');
			// 		}
			// 		$.messager.alert('提示',value.message);
			// 	}
			// });
		}	
	});	
}

/**
 * 删除
 */
function dele(id){

	$.messager.confirm('提示','确定要删除此记录吗？',function(r){
		if(r)
		{
			$.ajax({
                url: baseUrl + '/mdtTeam/delete?id='+id,
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
    layer.open({
        type: 2,
        title: 'MDT团队',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtTeamEdit.html?id=' + id
    });
}

function doSearch() {
    $('#grid').datagrid('reload');
}