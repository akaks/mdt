
$(function(){

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'proposer',title:'申请人',width:100},
        {field:'name',title:'MDT名称',width:200},
        {field:'date',title:'申请日期',width:100},
        {field:'annualStatus',title:'年度评估状态',width:100,formatter:function(value,row,index) {
            if (row.annualStatus == '0') {
                return "未发起";
            } else if (row.annualStatus == '1') {
                return "已发起";
            } else if (row.annualStatus == '2') {
                return "已填写待审核";
            } else if (row.annualStatus == '3') {
                return "已审核";
            }
            return '';
        }},
        {field:'-',title:'操作',width:500,formatter:function(value,row,index) {
            var launchBtn = "<a href='#' onclick='launch("+row.id+")'>发起两年度评估</a> ";
            var editBtn = "<a href='#' onclick='edit("+row.id+")'>MDT团队首席专家填写</a> ";
            var deleBtn = "<a href='#' onclick='dele("+row.id+")'>删除</a> ";
            var auditBtn = "<a href='#' onclick='auditFun("+row.id+")'>待审核</a> ";

            var btn = '';
            if (row.annualStatus == '0') {
                btn += launchBtn;
            }
            if (row.annualStatus == '1') {
                btn += editBtn;
            }
            if (row.annualStatus == '2') {
                btn += auditBtn;
            }

            return btn;
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
		pagination:true
	});
	
	//条件查询
	$('#btnSearch').bind('click',function(){
        doSearch();
	});

});

/**
 * 审核
 */
function auditFun(id){

    layer.open({
        type: 2,
        title: 'MDT团队审核',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'a.html?id=' + id
    });
}

function launch(teamId) {
    $.ajax({
        url: baseUrl + '/mdtTeam/launchAnnualAssess?teamId='+teamId,
        dataType:'json',
        type:'post',
        success:function(value){
            $.messager.alert('提示',value.message);
            if(value.type == 'success'){
                doSearch();
            }
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
        content: 'mdtTeamTwoYearAssessEdit.html?teamId=' + id
    });
}

function doSearch() {
    var formdata=getFormData('searchForm');
    $('#grid').datagrid('load',formdata);
}