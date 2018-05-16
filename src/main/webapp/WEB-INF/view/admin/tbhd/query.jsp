<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/view-begin.jsp"%>
<!-- 主内容面板 -->
<div class="content-wrapper">
<section class="content-header">
	<h1>${param.menuName}</h1>
	<ol class="breadcrumb">
		<li><a href="${ctx}/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li class="active">${param.menuName}</li>
	</ol>
</section>
<section class="content">
	<div class="row">
	<div class="col-md-12">
	<div class="box box-default">
	<div class="box-header with-border">
		<h3 class="box-title"><small><i class="glyphicon glyphicon-th-list"></i></small></h3>
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
		<table id="tb_list"></table>
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
<!-- query form modal
======================================================================================================= -->
<form id="form_query">
<div id="modal_form_query" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal_label" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
	<div class="modal-header">
	   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	   <h4 class="modal-title" id="modal_label">筛选条件</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			 <label for="f_tcField">tcField：</label><input type="text" class="form-control input-sm" id="f_tcField"/>
		</div>
	</div>
	<div class="modal-footer">
	   <button type="reset" class="btn btn-default"><i class="glyphicon glyphicon-repeat"></i></button>
	   <button type="button" class="btn btn-primary" id="form_query_btn"><i class="glyphicon glyphicon-ok"></i></button>
	</div>
</div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</form>
<%@ include file="/include/view-end.jsp"%>
<!-- ======================================================================================================= -->
<script type="text/javascript" src="${statics}/res/chok/js/chok.auth.js"></script>
<script type="text/javascript" src="${statics}/res/chok/js/chok.view.query2.js"></script>
<script type="text/javascript">
/**********************************************************/
/* 全局函数 */
/**********************************************************/
$(function() {
	$chok.view.fn.selectSidebarMenu("${param.menuId}","${param.menuPermitId}","${param.menuName}");
	$chok.view.query.init.toolbar();
	$chok.view.query.init.modalFormQuery();
	$chok.view.query.init.table("${queryParams.f_page}","${queryParams.f_pageSize}");
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
});
/**********************************************************/
/* 初始化配置 */
/**********************************************************/
$chok.view.query.config.setPreFormParams = function(){
	$("#f_tcField").val(typeof("${queryParams.f_tcField}")=="undefined"?"":"${queryParams.f_tcField}");
};
$chok.view.query.config.formParams = function(p){
	p.tcField = $("#f_tcField").val();
    return p;
};
$chok.view.query.config.urlParams = function(){
	return {
			f_tcField : $("#f_tcField").val()
	};
};
// config-定义表格列
$chok.view.query.config.tableColumns = 
[
    {title:"id", field:"id", align:"center", valign:"middle", sortable:true},
    {title:"code", field:"code", align:"center", valign:"middle", sortable:true}
];
// config-是否显示复合排序
$chok.view.query.config.showMultiSort = true;
// config-默认排序字段
$chok.view.query.config.sortPriority = [{"sortName":"", "sortOrder":"asc"}];
// callback-加载数据成功后
$chok.view.query.callback.onLoadSuccess = function(){
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
};
// OVERWRITE-自定义工具栏
$chok.view.query.init.toolbar = function(){
	$("#bar_btn_exp").click(function(){
		$chok.view.query.fn.exp("exp.action", 
				                "tb_hd",
				                "tb_hd", 
				                "id,code",
				                "id,code");
	});
};
// OVERWRITE-表格第一二列
/* $chok.view.query.fn.getColumns = function(){
	return $.merge([],$chok.view.query.config.tableColumns);
}; */
// 用户自定义
$chok.view.fn.customize = function(){
};
</script>