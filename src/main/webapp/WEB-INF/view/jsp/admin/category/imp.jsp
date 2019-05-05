<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/view-begin.jsp"%>
<!-- 主内容面板 -->
<div class="content-wrapper">
	<section class="content-header">
		<h1>${param.menuName}<small>上传</small></h1>
		<ol class="breadcrumb">
			<li><a href="${ctx}/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
			<li><a href="query?menuId=${param.menuId}&menuName=${param.menuName}">${param.menuName}</a></li>
			<li class="active">上传</li>
		</ol>
	</section>
	<section class="content">
		<div class="box box-default">
			<div class="box-header with-border">
				<h3 class="box-title"><small><i class="glyphicon glyphicon-plus"></i></small></h3>
				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool" id="back"><i class="glyphicon glyphicon-arrow-left"></i></button>
				</div>
			</div>
			<div class="box-body">
				<form class="dataForm">
					<input id="myFile" name="myFile" type="file" multiple class="file-loading"/>
					<div id="kv-error-2" style="margin-top:10px;display:none"></div>
					<div id="kv-success-2" class="alert alert-success fade in" style="margin-top:10p;display:none"></div>
				</form>
			</div>
			<div class="box-footer">
				<a href="${ctx}/excel/upload.xlsx" target="_blank">上传模板.xlsx</a>
			</div>
		</div>
	</section>
</div>
<%@ include file="/include/view-end.jsp"%>
<!-- ======================================================================================================= -->
<script type="text/javascript" src="${staticexternal}/res/chok/js/chok.auth.js"></script>
<link rel="stylesheet" href="${staticexternal}/res/bs/plugin/fileinput/css/fileinput.min.css"/>
<script type="text/javascript" src="${staticexternal}/res/bs/plugin/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${staticexternal}/res/chok/js/chok.view.add.js"></script>
<script type="text/javascript" src="${staticinternal}${jspaction}.js"></script>
<script type="text/javascript">
var $param_menuId = "${param.menuId}";
var $param_menuPermitId = "${param.menuPermitId}";
var $param_menuName = "${param.menuName}";
var $queryParams = "${queryParams}";
</script>
