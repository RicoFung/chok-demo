/**********************************************************/
/** 全局函数 **/
/**********************************************************/
$(function(){
	/*
	 * 菜单定位
	 */
	$chok.view.fn.selectSidebarMenu($param_menuId,$param_menuPermitId,$param_menuName);
	/*
	 * 返回列表页
	 */ 
	$("#back").click(function(){
		location.href = "query?"+$chok.view.fn.getUrlParams($queryParams);
	});
	/*
	 * 导入文件
	 */ 
	$("#myFile").fileinput({
	    allowedFileExtensions : ['xlsx'],
	    uploadUrl: "imp2", // server upload action
	    uploadAsync: true,
	    minFileCount: 1,
	    maxFileCount: 1
	}).on('filepreupload', function(event, data, previewId, index) {
		// 配置csrfToken
		data.jqXHR.setRequestHeader(header, token);  
		// 进行自定义验证并返回如下所示的错误
       if (!$chok.validator.check()) {
           return {
               message: '表单信息不合法'//,
               //data: {key1: 'Key 1', detail1: 'Detail 1'}
           };
       }
	}).on('fileuploaderror', function(event, data, msg) {
 	    var form = data.form, files = data.files, extra = data.extra,
        response = data.response, reader = data.reader;
	    var out = '';
	    $.each(data.files, function(key, file) {
	        var fname = file.name;
	        out = out + '<li>' + '上传 # ' + (key + 1) + ' - '  +  fname + ' 失败.' + '</li>';
	    });
	    $('#kv-error-2 ul').append(out);
	    $('#kv-error-2').show(); 
	}).on('fileuploaded', function(event, data, previewId, index) {
        var form = data.form, files = data.files, extra = data.extra,
        response = data.response, reader = data.reader;
	    if(!response.success){
    		$.alert({title: "提示", type: "red", content: response.msg});
	    	return;
	    }
		$.alert({title: "提示", type: "green", content: "导入成功！"});
		//location.href = "query?"+$chok.view.fn.getUrlParams($queryParams);
	});
   /*  同步请求
   }).on('filebatchpreupload', function(event, data, id, index) {
	    $('#kv-success-2').html('<h4>Upload Status</h4><ul></ul>').hide();
	}).on('filebatchuploaderror', function(event, data, msg) {
 	    var form = data.form, files = data.files, extra = data.extra,
	        response = data.response, reader = data.reader;
	    var out = '';
	    $.each(data.files, function(key, file) {
	        var fname = file.name;
	        out = out + '<li>' + '上传 # ' + (key + 1) + ' - '  +  fname + ' 失败.' + '</li>';
	    });
	    $('#kv-error-2 ul').append(out);
	    $('#kv-error-2').show(); 
	}).on('filebatchuploadsuccess', function(event, data, previewId, index) {
 	   var form = data.form, files = data.files, extra = data.extra,
	        response = data.response, reader = data.reader;
	    var out = '';
	    $.each(data.files, function(key, file) {
	        var fname = file.name;
	        out = out + '<li>' + '上传 # ' + (key + 1) + ' - '  +  fname + ' 成功.' + '</li>';
	    });
	    $('#kv-success-2 ul').append(out);
	    $('#kv-success-2').show();
	    alert("上传图片成功！");
		location.href = "query?"+$chok.view.fn.getUrlParams("${queryParams}");
	}); */
});

/**********************************************************/
/* 自定义配置 */
/**********************************************************/
$chok.view.fn.customize = function(){
};
