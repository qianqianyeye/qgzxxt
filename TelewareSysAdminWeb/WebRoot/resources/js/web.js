var Web=function(){
	return {
		initTenantSelect:function(combobox,onLoadSuccess,onSelect){
			combobox.combobox({
			    url:'tenant/getTenantSelectWithAuth',
			    valueField:'TENANTID',
			    textField:'REMARK',
			    editable:false,
			    onLoadSuccess:function(){
			    	onLoadSuccess();
			    },
			    onSelect:function(record){
			    	var tenantId=record.TENANTID;
		    		if(record.isAdmin){
		    			record.TENANTID='';
		    			combobox.combobox('setValue', '');
		    			combobox.combobox('setText', record.REMARK);
		    		}
		    		onSelect(record);
		    		record.TENANTID=tenantId;
			    }
			});
		},
		validDigits :function(msearch_name,msearch_value){
			var result=true;
			var data = msearch_value.mvalid('digits');
			if(!data.result){
				var text=msearch_name.find('option:selected').text();
				$.mal({
					text : text+data.message,
					type : 'warning' 
				});
				msearch_value.val('');
				result=false;
			}
			return result;
		} ,
		initTenantSelect2:function(select,onLoadSuccess,onSelect){
			$.post('tenant/getTenantSelectWithAuth',function(data){
				if(data){
					$.each(data, function(i, tenant) {
						var op =$('<option tenantName="'+tenant.TENANTNAME+'" tenantType="'+tenant.TENANTTYPE+'" value="'+tenant.TENANTID+'">' + tenant.REMARK + '</option>')
						if(tenant.isAdmin){
							op.attr('isAdmin',tenant.isAdmin);
							op.attr('value','');
						}
						select.append(op);
					});
					select.val('');
					select.change(function(){
						var tenantId=select.val();
						var op =select.find('option:selected');
						if(onSelect){
							var obj={
									ISADMIN:op.attr('isAdmin'),
									TENANTNAME:op.attr('tenantName'),
									TENANTTYPE:op.attr('tenantType'),
									TENANTID:tenantId,
									REMARK:op.text()
								};
							onSelect(obj);
						}
					});
					if(onLoadSuccess){
						onLoadSuccess();
					}
				}
			},'json');
		},
		/**
		 * 获取请求地址的到项目名称的内容的地址路径
		 */
		getProjectURL:function(){  
		    var curWwwPath = window.document.location.href;  
		    //获取主机地址之后的目录，如： cis/website/meun.htm  
		    var pathName = window.document.location.pathname;  
		    var pos = curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8080  
		    var localhostPaht = curWwwPath.substring(0, pos); //获取带"/"的项目名，如：/cis  
		    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);  
		    var rootPath = localhostPaht + projectName+"/";  
		    return rootPath;  
		},
		/**
		 * 获取上传文件的指定路径地址的内容
		 */
		getUploadFileURL:function(){
//			return "http://10.0.7.56:8080/TlwAttach/";
			return "http://220.160.59.10/TlwAttach/";
		},
		/**
		 * 用JS获取浏览器参数的值
		 * @param name
		 * @returns
		 */
		getQueryString:function(name) {
		    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		    var r = window.location.search.substr(1).match(reg);
		    if (r != null) {
		        return unescape(decodeURI(r[2]));
		    }
		    return null;
		}
	}
}()

$(function(){
	
})