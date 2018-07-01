var onlyOpenTitle = "欢迎使用";//不允许关闭的标签的标题

$(function() {
	InitLeftMenu();
})



var _menus = {};
var mtabId=0;

//初始化左侧
function InitLeftMenu(selectedPanelname) {
	$.mloading();
	//$("#nav").accordion({animate:false,fit:true,border:false});
	var projectUrl = getProjectURL();
	var tenantid=$("#tenantid").text();
	//alert(tenantid)
	var urlPath = getProjectURL() + "module/findModulejson?tenantid="+tenantid;
	var params = {"tenantid":tenantid};
	$.ajax({
		url : urlPath,
		type : "post",
		dataType : "text",
		data : params,
		contentType : "text/plain",
		success : function(data) {//回调函数
			_menus = {};
			if (data) {
				_menus = eval('(' + data + ')');
				$.each(_menus.child, function(i, n) {
					var icon=n.icon;
					if(!icon || icon.indexOf('fa')==-1){
						icon="fa fa-table";
					}
					var li = $("<li></li>");
					$('#side-menu').append(li);
					var menulist = $('<a href="#"><i class="'+icon+'"></i> <span class="nav-label">' + n.menuname + '</span><span class="fa arrow"></span></a>');
					li.append(menulist);
					var ul = $('<ul class="nav nav-second-level"></ul>');
					li.append(ul);
					$.each(n.child, function(j, o) {
						ul.append($('<li><a ref="' + o.menuid + '" rel="' + o.url + '" class="J_menuItem" href="' + o.url + '">' + o.menuname + '</a></li>'));
					})

					if (i == 0) {
						if (selectedPanelname == null || selectedPanelname == "" || selectedPanelname == undefined) {
							selectedPanelname = n.menuname;
						}
					}

				});

				$('#side-menu').metisMenu();
				var mtabs = $("#content-main").mtabs();
				var addTab=function(data){
					var iframe = $('<iframe width="100%" height="100%" src="' + data.url + '" frameborder="0" seamless></iframe>');
					if(data.mode){
						$.mloading();
						iframe[0].onload=function(){
							$.mhideLoading();
						} 
					}
					mtabs.addTab({
						id : data.id,
						title : data.title,
						content :iframe,
						allowClose:data.allowClose,
						active:data.active
					})
				}
				var initTab=function(){
					var startPageUrl=$("#startPage a").attr('href');
					var versionLogUrl=$("#versionLog a").attr('href');
					var startPageText=$("#startPage span").text();
					mtabId+=1;
					$("#startPage a").attr('tabId','mtab'+mtabId);
					addTab({id:'mtab'+mtabId,title:startPageText,url:startPageUrl,allowClose:false,active:true});
					
				}
				initTab();
				$(".J_menuItem").on('click', function() {
					var tabId=$(this).attr("tabId");
					if(!tabId || !mtabs.containTab(tabId)){
						var text=$(this).text();
						var url = $(this).attr('href');
						if(!tabId){
							mtabId+=1;
							tabId="mtab"+mtabId;
						}
						addTab({
							id:tabId,
							title:text,
							url:url,
							mode:'append'
						});
						$(this).attr("tabId",tabId);
					}
					mtabs.setActTab(tabId);
					mtabs.locationTab(tabId);
					// $("#J_iframe").attr('src', url);
					return false;
				});
			}
			$.mhideLoading();
		},
		error : function(errormsg) {
			$.mhideLoading();
			$.mal({
				text : "出现异常错误信息：" + errormsg,
				type : 'error'
			});
		}
	});

	//选中第一个
	//var panels = $('#nav').accordion('panels');
	//var t = panels[0].panel('options').title;
	//$('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus.child, function(i, n) {
		$.each(n.child, function(j, o) {
			if (o.menuid == menuid) {
				icon += o.icon;
			}
		})
	})

	return icon;
}

function find(menuid) {
	var obj = null;
	$.each(_menus.child, function(i, n) {
		$.each(n.child, function(j, o) {
			if (o.menuid == menuid) {
				obj = o;
			}
		});
	});

	return obj;
}

function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<div style="width:100%;height:100%;overflow:auto;"><iframe scrolling="yes" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe></div>';
	return s;
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
