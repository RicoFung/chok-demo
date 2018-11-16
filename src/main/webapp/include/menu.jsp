<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--DEV --%>
<%-- 
<%
String appId = "3";
String userId = "3";
String account = "DEV";
%>
<script type="text/javascript">
/* js 全局变量 **********************************************************/
var $g_menuJson = 
	[
	{"tcCode":"Category management","tcOrder":"2-1","tcAppId":3,"tcAuthorityId":27,"pid":0,"id":1,"tcName":"分类管理","tcUrl":"/admin/category/query"},
	{"tcCode":"Model management","tcOrder":"2-2","tcAppId":3,"tcAuthorityId":2,"pid":0,"id":2,"tcName":"模型管理","tcUrl":"/admin/model/query"},
	{"tcCode":"Image management","tcOrder":"2-3","tcAppId":3,"tcAuthorityId":18,"pid":0,"id":14,"tcName":"图片管理","tcUrl":"/admin/image/query"},
	{"tcCode":"Test","tcOrder":"2-4","tcAppId":3,"tcAuthorityId":0,"pid":0,"id":100,"tcName":"测试","tcUrl":"/admin/tbhd/query"}
	];  
var $g_btnJson = null;
/************************************************************************/
$(function(){
	// 菜单初始化
	$chok.menu.init($g_menuJson);
	// 菜单查询
	$("#navSearchForm").submit(function(event) {
		event.preventDefault();
	});
});
</script>
 --%>
<%-- OAUTH2 SSO --%>
<%@ page import="chok.util.PropertiesUtil" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%
String appcode = PropertiesUtil.getValue("config/", "spring.application.name");
String account = SecurityContextHolder.getContext().getAuthentication().getName();
request.setAttribute("appcode", appcode);
request.setAttribute("account", account);
%>
<script type="text/javascript">
/* js 全局变量 **********************************************************/
var $g_menuJson = [];
var $g_btnJson = null;
/************************************************************************/
$(function(){
	var params = {appcode:"${appcode}", usercode:"${account}"};
	// 菜单初始化
	if ($g_menuJson.length < 1) {
		callMenuApi(params);
	} else {
		$chok.menu.init($g_menuJson);
	}
	// 菜单查询
	$("#navSearchForm").submit(function(event) {
		event.preventDefault();
		var thisParams = $.extend(params, {menuname:$("#menuName").val()});
		callMenuApi(thisParams);
	});
});
/**
 * 调用菜单api
 */
function callMenuApi(params) {
    $.ajax({
        type: "post",
        async: false,
        url: $ctx+"/admin/home/menu",
        data: params,
        dataType: 'JSON',
        success: function (result) {
        	if(result.success==false){
        		$.alert({title: "提示", type: "red", content: result.msg});
        		return;
        	}
        	$g_menuJson = result.data.menus;
        	$chok.menu.init($g_menuJson);
        },
        error: function (jqXHR, textStatus, errorThrown) {
    		$.alert({title: "提示", type: "red", content: jqXHR.status + "<br/>" + jqXHR.responseText});
        }
    });
}
</script>
<%-- CAS
<%@ page import="chok.cas.client.CasLoginUser" %>
<%@ page import="chok.cas.client.filter.CasAccessFilter" %>
<%
CasLoginUser o = (CasLoginUser)session.getAttribute(CasAccessFilter.LOGINER);
String appId = o==null?"":o.getString("appId");
String userId = o==null?"":o.getString("id");
String account = o==null?"":o.getString("tc_code");
String menuJson = o==null?"":o.getString("menuJson");
String btnJson = o==null?"":o.getString("btnJson");
request.setAttribute("LoginUser", o);
%>
 --%>
<%-- SSO
<%@ page import="chok.sso.client.AuthUser"%>
<%@ page import="chok.sso.client.filter.LoginFilter"%>
<%
AuthUser o = (AuthUser)session.getAttribute(LoginFilter.LOGINER);
String appId = o==null?"":o.getString("appId");
String userId = o==null?"":o.getString("id");
String account = o==null?"":o.getString("tc_code");
String menuJson = o==null?"":o.getString("menuJson");
String btnJson = o==null?"":o.getString("btnJson");
request.setAttribute("LoginUser", o);
%>
--%>
<%-- 用于生产环境
<script type="text/javascript">
/* js 全局变量 **********************************************************/
var $g_menuJson = <%=menuJson%>;
var $g_btnJson = <%=btnJson%>;
/************************************************************************/
$(function(){
	// nav
	$chok.nav.init($g_menuJson);
	// 导航菜单查询
	$("#navSearchForm").submit(function(event) {
		event.preventDefault();
		var url = $("#navSearchForm").attr('action');
		$.post(
			url, 
			{'tc_name':$("#menuName").val(), "tc_user_id":<%=userId%>, "tc_app_id":<%=appId%>},
		  	function(rv) {
				$chok.nav.init(JSON.parse(rv));
			}
		);
	});
});
</script>
 --%>