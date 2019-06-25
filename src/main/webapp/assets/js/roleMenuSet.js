var id;//选中的ID


$(function(){

    //表格数据初始化
    $('#grid').datagrid({
        url:baseUrl + '/role/findAll',
        loadFilter: function(data){
            return data.resultData;
        },
        columns:[[
            {field:'id',title:'角色编号',width:100},
            {field:'name',title:'角色名称',width:100}
        ]],
        singleSelect:true,
        onClickRow:clickRow
    });

});

	var clickRow=function(rowIndex,rowData){

		$('#tree').tree({
			url:baseUrl + '/menu/readRoleMenus?id='+rowData.id,
            loadFilter: function(data){
                return data.resultData.row;
            },
			animate:true,
			checkbox:true
		});
		id=rowData.id;
	}

	//保存
	function save(){

		var nodes= $('#tree').tree("getChecked");//得到选中的节点集合

		var checkedStr="";
		for(var i=0;i<nodes.length;i++){
			if(i>0){
				checkedStr+=','
			}
			checkedStr+= nodes[i].id ;
		}

		$.ajax({
			url:baseUrl + '/role/save',
			dataType:'json',
			type:'post',
			data:{roleId:id,checkedStr:checkedStr},
			success:function(value){
				$.messager.alert('提示',value.message);
			}

		});

	}