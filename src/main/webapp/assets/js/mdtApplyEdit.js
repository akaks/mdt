var id;
var type;  // 类型 区分 新增、编辑、审核等

$(function(){

    id = getQueryVariable("id");
    type = getQueryVariable("type");

    if(id != undefined && id != null){
        initData(id);

        initGrid1(id);
    } else {

        getMdtApplyKey();

        getMdtPurpose();
    }


    if(type != undefined && type != null){
        if (type == 'add') {

            $("#btn1").show();
            $("#btn2").show();
        }
        if (type == 'edit') {

            $("#btn1").show();
        }
        if (type == 'view') {

            $("#audit1").show();
            $("#audit2").show();
            $("#audit3").show();
            $("#audit4").show();
        }
    }
});

// 获取MDT目的的多选框值
function getMdtPurpose() {
    $.ajax({
        url: baseUrl + '/set/getCodeByType?type=1',
        data:{},
        dataType:'json',
        type:'post',
        success:function(value){

                console.log(value)
            if(value.type == 'success'){

                var data = value.resultData.rows;
                var box = '';
                for (var i=0; i<data.length; i++) {

                    if ($("#mdtPurpose").val().indexOf(data[i].code) != -1) {

                        box += '<input type="checkbox" name="mdtPurposeBox" onclick="changePurposeBox()" checked value="'+ data[i].code +'">' + data[i].value

                    } else {

                        box += '<input type="checkbox" name="mdtPurposeBox" onclick="changePurposeBox()" value="'+ data[i].code +'">' + data[i].value
                    }

                }

                $("#mdtPurposeSpan").html(box);
            }
        }
    });
}

function changePurposeBox() {
    var val = '';
    $('input[name="mdtPurposeBox"]:checked').each(function(){
        val += $(this).val() + ',';
    });

    $("#mdtPurpose").val(val);
}

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
        {field:'-',title:'操作',width:200,hidden: (type != 'add' && type != 'edit'),formatter:function(value,row,index) {
            return "<a href='#' onclick='delApplyDoctor("+row.id+")'>删除</a>";
        }}
    ]];

    var toolbar;
    if (type == 'edit' || type == 'add') {
        toolbar = [{
            iconCls: 'icon-add',
            text:'添加专家',
            handler: function(){

                addTeamInfo(id);
            }
        }]
    } else {
        toolbar = [];
    }

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
        toolbar: toolbar

    });
}


// 获取主键
function getMdtApplyKey() {
    $.ajax({
        url: baseUrl + '/mdtApply/getMdtApplyKey',
        data:{},
        dataType:'json',
        type:'post',
        success:function(value){

            if(value.type == 'success'){
                id = value.resultData.row;
                $("#id").val(id);

                initGrid1(id);
            }
        }
    });
}

function addTeamInfo(id) {

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
            if(value.type == 'success'){
                var mylay = parent.layer.getFrameIndex(window.name);
                parent.layer.close(mylay);

                window.parent.doSearch();
            } else {

                $.messager.alert('提示',value.message);
            }
        }
    });
}

function commintAudit() {
    $("#applyStatus").val('1');

    save();
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
                    if(value.type == 'success'){
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
function initData(id){
    $.ajax({
        url: baseUrl + '/mdtApply/get?id='+id,
        dataType:'json',
        type:'post',
        success:function(value){
            if(value.type == 'success'){
                var data = value.resultData.row;
                $('#editForm').form('load', value.resultData.row);
                $('#editForm2').form('load', value.resultData.row);

                if (type == 'edit' && $("#applyStatus").val() == '0') {
                    $("#btn2").show();
                }

                // 住院病人，需要审核
                if (type == 'audit' && data.patientType == '1') {

                    if (data.applyStatus == '1') {
                        $("#audit1").show();
                        $("#audit2").show();
                    } else if (data.applyStatus == '2') {
                        $("#audit3").show();
                        $("#audit4").show();
                    }
                    $("#btn4").show();
                }

                if (type == 'edit') {
                    if (data.applyStatus == '9') {
                        $("#btn2").show();
                    }
                }

                $("#id").val(data.id);

                getMdtPurpose();
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


function auditSave() {

    //判断：编辑表单的所有控件是否都通过验证
    var isValidate= $('#editForm2').form('validate');
    if(isValidate==false){
        return ;
    }

    var formdata=getFormData('editForm2');

    $.ajax({
        url: baseUrl + '/mdtApply/audit',
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