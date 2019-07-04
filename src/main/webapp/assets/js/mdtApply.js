
$(function(){

    var columns=[[
        /*{field:'id',title:'编号',width:100},*/
        {field:'patientType',title:'患者类型',width:70,formatter:function(value,row,index) {
            if (row.patientType == '1') {
                return "住院";
            } else if (row.patientType == '2') {
                return "门诊";
            }
            return '';
        }},
        {field:'name',title:'患者姓名',width:120},
        {field:'gender',title:'性别',width:50,formatter:function(value,row,index) {
            if (row.gender == '1') {
                return "男";
            } else if (row.gender == '2') {
                return "女";
            }
            return '';
        }},
        {field:'diagnoseDate',title:'入院/首诊时间',width:120},
        {field:'mdtDate',title:'MDT时间',width:120},
        {field:'mdtLocation',title:'MDT地点',width:150},
        {field:'applyStatus',title:'申请人状态',width:150,formatter:function(value,row,index) {
            if (row.applyStatus == '0') {
                return "申请人申请";
            } else if (row.applyStatus == '1') {
                return "科主任已审核";
            } else if (row.applyStatus == '2') {
                return "医务部主任已审核";
            }
            return '';
        }},

        {field:'-',title:'操作',width:500,formatter:function(value,row,index) {
            var editBtn = "<a href='#' onclick='edit("+row.id+")'>修改</a> ";
            var auditBtn = "<a href='#' onclick='auditFun("+row.id+")'>审核</a> ";
            var feeBtn = "<a href='#' onclick='feeFun("+row.id+")'>打印缴纳单</a> ";
            var msgBtn = "<a href='#' onclick='msgFun("+row.id+")'>短信通知</a> ";
            var informBtn = "<a href='#' onclick='informFun("+row.id+")'>知情同意书</a> ";
            var consultBtn = "<a href='#' onclick='consultFun("+row.id+")'>MDT会诊</a> ";
            var expertGradeBtn = "<a href='#' onclick='expertGradeFun("+row.id+")'>专家打分</a> ";
            var departmentGradeBtn = "<a href='#' onclick='departmentGradeFun("+row.id+")'>组织科室打分</a> ";
            var deleBtn = "<a href='#' onclick='dele("+row.id+")'>删除</a> ";

            var btn = "";
            btn = btn + editBtn;

            var roleIds = getUser().roleIds;

            // 住院病人，需要审核
            if (row.patientType == '1' && (row.applyStatus == '0' || row.applyStatus == '1') ) {

                // 科室主任
                if (roleIds.indexOf('5') != -1 && row.applyStatus == '0') {

                    btn = btn + auditBtn;
                }

                // 医务部主任
                if (roleIds.indexOf('3') != -1 && row.applyStatus == '1') {

                    btn = btn + auditBtn;
                }

            }

            // 住院病人
            if (row.patientType == '1' && row.applyStatus == '2' ) {
                if (row.isCharge == '1') {

                    btn = btn + feeBtn;
                }

                btn = btn + informBtn + msgBtn;

                // 专家
                if (roleIds.indexOf('6') != -1) {

                    btn = btn + expertGradeBtn;
                }

                // 组织科室
                if (roleIds.indexOf('5') != -1 || roleIds.indexOf('7') != -1) {

                    btn = btn + departmentGradeBtn;
                }

            }

            // 门诊病人
            if (row.patientType == '2' && row.applyStatus == '0' ) {
                btn = btn + informBtn;
            }

            btn = btn + deleBtn;

            return btn;
        }}
    ]];

    var toolbar;
    if (getUser().roleIds.indexOf('7') != -1) {

        toolbar = [{
            iconCls: 'icon-add',
            text:'增加',
            handler: function(){

                layer.open({
                    type: 2,
                    title: 'MDT申请',
                    maxmin: true,
                    shadeClose: true, //点击遮罩关闭层
                    area : ['80%' , '80%'],
                    content: 'mdtApplyEdit.html'
                });
            }
        }]
    } else {
        toolbar = []
    }

	//表格数据初始化
	$('#grid').datagrid({
		url:baseUrl + '/mdtApply/findByPage',
        loadFilter: function(data){
            return data.resultData;
        },
		columns:columns,
		singleSelect:true,
		pagination:true,
		toolbar: toolbar

	});
	
	//条件查询
	$('#btnSearch').bind('click',function(){
        doSearch();
	});

});

// 审核
function auditFun(id){

    layer.open({
        type: 2,
        title: 'MDT申请审核',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyAudit.html?id=' + id
    });
}

// 打印缴纳单
function feeFun(id) {

    layer.open({
        type: 2,
        title: 'MDT缴费通知',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyFee.html?id=' + id
    });
}

// 短信通知
function msgFun(id) {

    layer.open({
        type: 2,
        title: 'MDT短信通知',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyMsg.html?id=' + id
    });
}

// 知情同意书
function informFun(id) {

    layer.open({
        type: 2,
        title: 'MDT知情同意书',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyInform.html?id=' + id
    });
}

// MDT会诊
function consultFun() {

}

// MDT会诊
function expertGradeFun(id) {

    layer.open({
        type: 2,
        title: '专家打分',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyExpertGrade.html?id=' + id
    });
}

// MDT会诊
function departmentGradeFun(id) {

    layer.open({
        type: 2,
        title: '组织科室打分',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyDeptGrade.html?id=' + id
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
    layer.open({
        type: 2,
        title: 'MDT申请',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area : ['80%' , '80%'],
        content: 'mdtApplyEdit.html?id=' + id
    });
}

function doSearch() {
    var formdata=getFormData('searchForm');
    $('#grid').datagrid('load',formdata);
}