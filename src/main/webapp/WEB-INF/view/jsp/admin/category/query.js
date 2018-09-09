/**********************************************************/
/* 全局函数 */
/**********************************************************/
$(function() {
	$chok.view.fn.selectSidebarMenu($param_menuId, $param_menuPermidId, $param_menuName);
	$chok.view.query.init.toolbar();
	$chok.view.query.init.modalFormQuery();
	$chok.view.query.init.table($queryParams_f_page, $queryParams_f_pageSize);
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
});
/**********************************************************/
/* 初始化配置 */
/**********************************************************/
$chok.view.query.config.setPreFormParams = function(){
	$("#f_name").val(typeof($queryParams_f_name)=="undefined"?"":$queryParams_f_name);
};
$chok.view.query.config.formParams = function(p){
	p.name = $("#f_name").val();
    return p;
};
$chok.view.query.config.urlParams = function(){
	return {f_name : $("#f_name").val()};
};
$chok.view.query.config.tableColumns = 
[
    {title:'ID', field:'m.id', align:'center', valign:'middle', sortable:true},
    {title:'名称', field:'m.name', align:'center', valign:'middle', sortable:true, 
    	editable:
    	{
    		type:'text',
   			title:'名称',
	    	validate: function(value){
	            return $chok.validator.checkEditable("required", null, value, null);
	    	}
    	}
    },
    {title:'排序', field:'m.sort', align:'center', valign:'middle', sortable:true, 
    	editable:
    	{
	    	type:'text',
	    	title:'排序',
	    	validate: function(value){
	            return $chok.validator.checkEditable("number", null, value, null);
	    	}
    	}
    }
];
$chok.view.query.config.showMultiSort = true;
$chok.view.query.config.sortPriority = [{"sortName":"m.sort", "sortOrder":"asc"}];
$chok.view.query.callback.delRows = function(){
};
$chok.view.query.callback.onLoadSuccess = function(){
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
};
/* OVERWRITE-初始化工具栏 */
$chok.view.query.init.toolbar = function(){
	$("#bar_btn_imp").click(function(){
		location.href = "imp?"+$chok.view.query.fn.getUrlParams();
	});
	$("#bar_btn_exp").click(function(){
		$chok.view.query.fn.exp('exp', 'test','test_title', 'ID,名称,排序', 'id,name,sort');
	});
	$("#bar_btn_add").click(function(){
		location.href = "add?"+$chok.view.query.fn.getUrlParams();
	});
	$("#bar_btn_del").click(function(){
		if($chok.view.query.fn.getIdSelections().length<1) {
			$.alert({title: "提示", type: "red", content: "没选择"});
			return;
		}
		$.confirm({
		    title: '提示',
		    content: "确认删除？",
		    type: 'red',
		    typeAnimated: true,
		    buttons: {
		        ok: function() {
			    		$.post("del",{id:$chok.view.query.fn.getIdSelections()},function(result){
			    	        $chok.view.query.callback.delRows(result); // 删除行回调
			    	        if(!result.success) {
				    	        	$.alert({title: "提示", type:"green", content: result.msg});
				    	        	return;
			    	        }
			    	        $("#tb_list").bootstrapTable('refresh'); // 刷新table
			    		});
		        },
		        close: function () {
		        }
		    }
		});
	});
};