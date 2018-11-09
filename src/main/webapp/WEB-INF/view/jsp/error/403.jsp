<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/view-begin.jsp"%>
<!-- 主内容面板 -->
<div class="content-wrapper">
	<section class="content-header">
		<h1>403 Error Page</h1>
		<ol class="breadcrumb">
			<li><a href="${ctx}/index.jsp"><i class="fa fa-dashboard"></i>
					首页</a></li>
			<li class="active">403 error</li>
		</ol>
	</section>
	<section class="content">
		<div class="error-page">
			<h2 class="headline text-yellow">403</h2>
			<div class="error-content">
				<h3>
					<i class="fa fa-warning text-yellow"></i> Oops!
				</h3>
				<p>${msg}.</p>
			</div>
	</section>
</div>
<%@ include file="/include/view-end.jsp"%>