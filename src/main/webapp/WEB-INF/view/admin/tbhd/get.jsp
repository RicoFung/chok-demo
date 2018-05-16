<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/view-begin.jsp"%>
<!-- 主内容面板 -->
<div class="content-wrapper">
	<section class="content-header">
		<h1>${param.menuName}<small>明细</small></h1>
		<ol class="breadcrumb">
			<li><a href="${ctx}/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
			<li><a href="query.action?menuId=${param.menuId}&menuName=${param.menuName}">${param.menuName}</a></li>
			<li class="active">明细</li>
		</ol>
	</section>
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<!-- 主表信息 -->
				<div class="box box-default">
					<div class="box-header with-border">
						<h3 class="box-title"><small><i class="glyphicon glyphicon-info-sign"></i></small>&nbsp;&nbsp;HD</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							<button type="button" class="btn btn-box-tool" id="back"><i class="glyphicon glyphicon-arrow-left"></i></button>
						</div>
					</div>
					<div class="box-body">
						<form class="dataForm" id="dataForm" role="form">
							<div class="form-group"><label class="control-label" for="name">ID：</label><input type="text" class="form-control input-sm" id="id" name="id" value="${po.id}" readonly="readonly"/></div>
							<div class="form-group"><label class="control-label" for="sort">CODE：</label><input type="text" class="form-control input-sm" id="code" name="code" value="${po.code}" readonly="readonly"/></div>
						</form>
					</div>
					<div class="box-footer">
					&nbsp;
					</div>
				</div>
				<!-- 明细信息 -->
				<div class="box box-default">
					<div class="box-header with-border">
						<h3 class="box-title"><small><i class="glyphicon glyphicon-th-list"></i></small>&nbsp;&nbsp;DT</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
						</div>
					</div>
					<div class="box-body">
						<!-- toolbar
						======================================================================================================= -->
						<div id="toolbar">
							<button type="button" class="btn btn-default" id="bar_btn_query" pbtnId="pbtn_query2" data-toggle="modal" data-target="#modal_form_query"><i class="glyphicon glyphicon-search"></i></button>
							<button type="button" class="btn btn-default" id="bar_btn_exp" ><i class="glyphicon glyphicon-download"></i></button>
						</div>
						<!-- data list
						======================================================================================================= -->
						<table id="tb_dt_list"></table>
						<!-- context menu
						======================================================================================================= -->
						<ul id="tb_ctx_menu" class="dropdown-menu">
						    <li data-item="get" class="get" pbtnId="pbtn_get"><a><i class="glyphicon glyphicon-info-sign"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<%@ include file="/include/view-end.jsp"%>
<!-- ======================================================================================================= -->
<script type="text/javascript" src="${statics}/res/chok/js/chok.view.get.js"></script>
<script type="text/javascript">
/**********************************************************/
/* 全局函数 */
/**********************************************************/
$(function(){
	$chok.view.fn.selectSidebarMenu("${param.menuId}","${param.menuPermitId}","${param.menuName}");
	// 返回列表页
	$("#back").click(function(){
		location.href = "query.action?"+$chok.view.fn.getUrlParams("${queryParams}");
	});
});
//
$chok.view.fn.customize = function(){
	$('#tb_dt_list').bootstrapTable({
		height:$chok.view.fn.getGlobalHeight("table"),
		toolbar: "#toolbar",
		//contextMenu: "#tb_ctx_menu",
		//contextMenuButton: '.tb_ctx_menu_btn',
		contentType: "application/x-www-form-urlencoded",//用post，必须采用此参数
		ajax: "ajaxRequest",//自定义ajax
		sidePagination: "server",
        showRefresh: true,
        showToggle: true,
        showColumns: true,
        showExport: true,
        showMultiSort: true,
        sortPriority: [{"sortName":"", "sortOrder":"asc"}],
		striped: true,
		pagination: true,
		pageList: "[5,10,20,50,100]",
		pageNumber: 1,
		pageSize: 10,
	    queryParams: function(p){
	    		p.pid = "${po.id}";
	    		return p;
	    },
	    columns: 
	    	[
	    	    {title:"ID", field:"id", align:"center", valign:"middle", sortable:true},
	    	    {title:"PID", field:"pid", align:"center", valign:"middle", sortable:true},
	    	    {title:"CODE", field:"code", align:"center", valign:"middle", sortable:true}
		],
	    onLoadSuccess: function(){},
	    onLoadError: function(status){}
	    //onPageChange: $chok.view.query.callback.onPageChange,
        //onEditableSave: $chok.view.query.callback.onEditableSave,
        //onContextMenuItem: $chok.view.query.callback.onContextMenuItem
	});
/* 	//随窗口resize 改变 高度
	$(window).resize(function () {
		$('#tb_dt_list').bootstrapTable('resetView', {height: $(window).height()-550});
	});
	//随导航菜单resize 改变 宽度
	$("section[class='content']").resize(function(){
		$('#tb_dt_list').bootstrapTable('resetView');
	}); */
};
function ajaxRequest(params){
	$.LoadingOverlay("show");
    //访问服务器获取所需要的数据
    //比如使用$.ajax获得请求某个url获得数据
    $.ajax({
        type : 'post',
        url : '${ctx}/admin/tbdt/query2.action',
        data : params.data,
        success : function(result){
        		$.LoadingOverlay("hide");
	        	if(result.success==false){
	        		$.alert({title: "提示", content: result.msg});
	        		return;
	        	}
	        //表格加载数据
	        params.success({
	            total : result.total,
	            rows : result.rows
	        });
        }
    });
}
</script>