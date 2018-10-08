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
		<button type="button" class="btn btn-default" id="bar_btn_add" pbtnId="pbtn_add"><i class="glyphicon glyphicon-plus"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_del" pbtnId="pbtn_del"><i class="glyphicon glyphicon-remove"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_query" pbtnId="pbtn_query2" data-toggle="modal" data-target="#modal_form_query"><i class="glyphicon glyphicon-search"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_imp" ><i class="glyphicon glyphicon-upload"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_exp" ><i class="glyphicon glyphicon-download"></i></button>
		</div>
		<!-- data list
		======================================================================================================= -->
		<table id="tb_list"></table>
		<!-- context menu
		======================================================================================================= -->
		<ul id="tb_ctx_menu" class="dropdown-menu">
		    <li data-item="upd" class="upd" pbtnId="pbtn_upd"><a><i class="glyphicon glyphicon-edit"></i></a></li>
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
			 <label for="f_name">名称：</label><input type="text" class="form-control input-sm" id="f_name"/>
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
<script type="text/javascript" src="${staticexternal}/res/chok/js/chok.auth.js"></script>
<script type="text/javascript" src="${staticexternal}/res/chok/js/chok.view.query.js"></script>
<script type="text/javascript" src="${staticinternal}${jspaction}.js"></script>
<script type="text/javascript">
var $param_menuId = "${param.menuId}";
var $param_menuPermidId = "${param.menuPermitId}";
var $param_menuName = "${param.menuName}";
var $queryParams_f_page = "${queryParams.f_page}";
var $queryParams_f_pageSize = "${queryParams.f_pageSize}";
var $queryParams_f_name = "${queryParams.f_name}";
</script>