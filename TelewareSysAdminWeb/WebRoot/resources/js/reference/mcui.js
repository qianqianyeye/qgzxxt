(function($) {

	$.mal = function(obj, fn_ok, fn_cansel) {
		var swalo = {
			title : obj.title,
			text : obj.text,
			icon : obj.type
		// warning error success info
		};
		if (fn_ok) {
			var buttons = obj.buttons ? obj.buttons : false;
			swalo.buttons = buttons;
			swalo.buttons = [ '取消', '确定' ];
			swalo.dangerMode = true;
			swal(swalo).then(function(willDelete) {
				if (willDelete) {
					fn_ok();
					swal.close();
				} else {
					if (fn_cansel) {
						fn_cansel();
					}
				}
			});
		} else {
			if (obj.button) {
				swalo.button = obj.button;
			} else {
				swalo.button = false;
				var time = obj.time ? obj.time : 1200;
				swalo.timer = time;
			}
			swal(swalo);
		}
	}

	$.mloading = function() {
		var modal = $('<div class="modal inmodal" mc=true name="modal-dialog-three-bounce" tabindex="-1" role="dialog" aria-hidden="true"></div>');
		var dialog = $('<div class="modal-dialog" style="width:100%;height:100%;margin:0;"></div>');
		var three = $('<div class="sk-spinner sk-spinner-three-bounce" style="position: relative;top: 49%;"><div class="sk-bounce1"></div><div class="sk-bounce2"></div><div class="sk-bounce3"></div></div>');
		modal.append(dialog);
		dialog.append(three);
		$('body').append(modal);
		modal.modal('show');
	}

	$.mhideLoading = function() {
		$("[name=modal-dialog-three-bounce][mc=true]").modal('hide');
	}

	$.fn.mtabs = function (c) {
        var h = this;
        var g = {allowClose: true, active: false, rollWidth: h.width() - 120,};
        var e = $.extend({}, g, c);
        var d = '<div class="page-tabs"><a href="javascript:void(0);" class="roll-nav roll-nav-left"><span class="fa fa-backward"></span></a><div class="content-tabs" style="border-bottom:none"><div class="content-tabs-container"><ul class="nav nav-tabs"></ul></div></div><a href="javascript:void(0);" class="roll-nav roll-nav-right"><span class="fa fa-forward"></span></a></div><div class="tab-content"></div>';
        var b = {
            init: function () {
                h.html(d);
                b.listen()
            }, listen: function () {
                f.onTabClose().onTabRollLeft().onTabRollRight().onTabList().onTabCloseOpt().onTabCloseAll().onTabCloseOther().onLocationTab()
            }, getAllTabWidth: function () {
                var i = 0;
                h.find(".nav-tabs li").each(function () {
                    i += parseFloat($(this).width())
                });
                return i
            }, getMarginStep: function () {
                return e.rollWidth / 2
            }, getActiveId: function () {
                return h.find('li[class="active"]').find("a").attr("href").replace("#", "")
            }, getTabList: function () {
                var i = [];
                h.find(".nav-tabs li a").each(function () {
                    i.push({id: $(this).attr("href"), title: $(this).children("span").html()})
                });
                return i
            },containTab:function(id){
            	var result=false;
            	var aid;
            	h.find(".nav-tabs li a").each(function () {
            		aid=$(this).attr("href");
            		if(aid==('#'+id)){
            			result=true;
            			return false;
            		}
                });
            	return result;
            }, addTab: function (j) {
                var k = [];
                var m = j.active == undefined ? e.active : j.active;
                var i = j.allowClose == undefined ? e.allowClose : j.allowClose;
                m = m ? "active" : "";
                k.push('<li role="presentation" class="' + m + '">');
                k.push('<a href="#' + j.id + '" data-toggle="tab">');
                k.push("<span>" + j.title + "</span>");
                i ? k.push('<i class="fa fa-close tab-close"></i>') : "";
                k.push("</a>");
                k.push("</li>");
                var li=$(k.join(""));
                h.find(".nav-tabs").append(li);
                var l = [];
                l.push('<div class="tab-pane ' + m + '" id="' + j.id + '">');
                l.push("</div>");
                var content=$(l.join(""));
                content.append(j.content);
                h.find(".tab-content").append(content);
                return b
            }, locationTab: function (j) {
                j = j == undefined ? b.getActiveId() : j;
                j = j.indexOf("#") > -1 ? j : "#" + j;
                var m = h.find("[href='" + j + "']");
                var l = 0;
                m.parent().prevAll().each(function () {
                    l += $(this).width()//该元素之前的li总宽度
                });
                var i = m.parent().parent().parent();
                if (l <= e.rollWidth * 0.7) {
                    margin_left_total = 40
                } else {
                    if (l <= e.rollWidth) {
                        margin_left_total = 40 - e.rollWidth / 2
                    } else {
                        var k = l + m.parent().width() - (Math.floor(l / e.rollWidth) * e.rollWidth);
                        if (k <= e.rollWidth * 0.7) {
                            margin_left_total = 40 - Math.floor(l / e.rollWidth) * e.rollWidth
                        } else {
                            margin_left_total = 40 - Math.floor(l / e.rollWidth) * e.rollWidth - e.rollWidth / 2
                        }
                    }
                }
                i.css("margin-left", margin_left_total);
                return b
            }, delTab: function (j) {
                j = j == undefined ? b.getActiveId() : j;
                j = j.indexOf("#") > -1 ? j : "#" + j;
                var k = h.find("[href='" + j + "']");
                if (k.parent().attr("class") == "active") {
                    var i = k.parent().next();
                    var l = $(j).next();
                    if (i.length < 1) {
                        i = k.parent().prev();
                        l = $(j).prev()
                    }
                    i.addClass("active");
                    l.addClass("active")
                }
                k.parent().remove();
                $(j).remove();
                return b
            }, delOtherTab: function () {
                h.find(".nav-tabs li").not('[class="active"]').remove();
                h.find(".tab-content div").not('[class="tab-pane active"]').remove();
                return b
            }, delAllTab: function () {
                h.find(".nav-tabs li").remove();
                h.find(".tab-content div").remove();
                return b
            }, setActTab: function (i) {
                i = i == undefined ? b.getActiveId() : i;
                i = i.indexOf("#") > -1 ? i : "#" + i;
                h.find(".active").removeClass("active");
                h.find("[href='" + i + "']").parent().addClass("active");
                h.find(i).addClass("active");
                return b
            },
        };
        var f = {
            onLocationTab: function () {
                h.on("click", ".tab-location", function () {
                    b.locationTab()
                });
                return f
            }, onTabClose: function () {
                h.on("click", ".tab-close", function () {
                    var i = $(this).parent().attr("href");
                    b.delTab(i)
                });
                return f
            }, onTabCloseOpt: function () {
                h.on("click", ".tab-close-current", function () {
                    b.delTab()
                });
                return f
            }, onTabCloseOther: function () {
                h.on("click", ".tab-close-other", function () {
                    b.delOtherTab()
                });
                return f
            }, onTabCloseAll: function () {
                h.on("click", ".tab-close-all", function () {
                    b.delAllTab()
                });
                return f
            }, onTabRollLeft: function () {
                h.on("click", ".roll-nav-left", function () {
                    var i = $(this).parent().find(".content-tabs-container");
                    var j = i.css("marginLeft").replace("px", "");
                    if (b.getAllTabWidth() <= e.rollWidth && parseFloat(j)>0) {
                        return false
                    }
                    var k = parseFloat(j) + b.getMarginStep() + 40;
                    i.css("margin-left", k > 40 ? 40 : k)
                });
                return f
            }, onTabRollRight: function () {
                h.on("click", ".roll-nav-right", function () {
                    if (b.getAllTabWidth() <= e.rollWidth) {
                        return false
                    }
                    var i = $(this).parent().find(".content-tabs-container");
                    var j = i.css("marginLeft").replace("px", "");
                    var k = parseFloat(j) - b.getMarginStep();
                    if (b.getAllTabWidth() - Math.abs(j) <= e.rollWidth) {
                        return false
                    }
                    i.css("margin-left", k)
                });
                return f
            }, onTabList: function () {
                h.on("click", ".right-nav-list", function () {
                    var i = b.getTabList();
                    var j = [];
                    $.each(i, function (k, l) {
                        j.push('<li class="toggle-tab" data-id="' + l.id + '">' + l.title + "</li>")
                    });
                    h.find(".tab-list").html(j.join(""))
                });
                h.find(".tab-list-scrollbar").scrollbar();
                f.onTabListToggle();
                return f
            }, onTabListToggle: function () {
                h.on("click", ".toggle-tab", function () {
                    var i = $(this).data("id");
                    b.setActTab(i).locationTab(i)
                });
                return f
            }
        };
        b.init();
        return b
    }
	
	$.fn.extend({
		mvalid:function(type){
			var data={result:true};
			var value=$(this).val() ;
			if(value){
				switch (type) {
			    case "digits":
			    	data.result=/^\d+$/.test(value);
			    	if(!data.result){
			    		data.message="只能输入数字";
			    	}
			        break;
				}
			}
			return data;
		},
        mform:function(options){  
            var defaults = {  
                jsonValue:options,  
                isDebug:false   //是否需要调试，这个用于开发阶段，发布阶段请将设置为false，默认为false,true将会把name value打印出来  
            }  
            var setting = defaults;  
            var form = this;  
            jsonValue = setting.jsonValue;  
            if($.type(setting.jsonValue) === "string"){  
                jsonValue = $.parseJSON(jsonValue);  
            }  
            if(!$.isEmptyObject(jsonValue)){  
                var debugInfo = "";  
                $.each(jsonValue,function(key,value){  
                    if(setting.isDebug){  
                        alert("name:"+key+"; value:"+value);  
                        debugInfo += "name:"+key+"; value:"+value+" || ";  
                    }  
                    var formField = form.find("[name='"+key+"']");  
                    if($.type(formField[0]) === "undefined"){  
                        if(setting.isDebug){  
                            alert("can not find name:["+key+"] in form!!!");    //没找到指定name的表单  
                        }  
                    } else {  
                        var fieldTagName = formField[0].tagName.toLowerCase();  
                        if(fieldTagName == "input"){  
                            if(formField.attr("type") == "radio"){  
                                $("input:radio[name='"+key+"'][value='"+value+"']").attr("checked","checked");  
                            } else {  
                                formField.val(value);  
                            }  
                        } else if(fieldTagName == "select"){  
                            formField.val(value);  
                        } else if(fieldTagName == "textarea"){  
                            formField.val(value);
                        } else {  
                            formField.val(value);  
                        }  
                    }  
                })  
                if(setting.isDebug){  
                    alert(debugInfo);  
                }  
            }  
            return form;    //返回对象，提供链式操作  
        }  
    });
	
/******************************************************************************************/
	/*! 分页的底部按钮脚本  button */
	$.fn.extendPagination = function (options) {
        var defaults = {
            totalCount: '',
            showPage: '10',
            limit: '5',
            callback: function () {
                return false;
            }
        };
        $.extend(defaults, options || {});
        if (defaults.totalCount == '') {
            $(this).empty();
            return false;
        } else if (Number(defaults.totalCount) <= 0) {
            $(this).empty();
            return false;
        }
        if (defaults.showPage == '') {
            defaults.showPage = '10';
        } else if (Number(defaults.showPage) <= 0)defaults.showPage = '10';
        if (defaults.limit == '') {
            defaults.limit = '5';
        } else if (Number(defaults.limit) <= 0)defaults.limit = '5';
        var totalCount = Number(defaults.totalCount), showPage = Number(defaults.showPage),
            limit = Number(defaults.limit), totalPage = Math.ceil(totalCount / limit);
        if (totalPage > 0) {
            var html = [];
            html.push(' <ul class="pagination">');
            html.push(' <li class="previous"><a href="JavaScript:;">&laquo;</a></li>');
            html.push('<li class="disabled hidden"><a href="JavaScript:;">...</a></li>');
            if (totalPage <= showPage) {
                for (var i = 1; i <= totalPage; i++) {
                    if (i == 1) html.push(' <li class="active"><a href="JavaScript:;">' + i + '</a></li>');
                    else html.push(' <li><a href="JavaScript:;">' + i + '</a></li>');
                }
            } else {
                for (var j = 1; j <= showPage; j++) {
                    if (j == 1) html.push(' <li class="active"><a href="JavaScript:;">' + j + '</a></li>');
                    else html.push(' <li><a href="JavaScript:;">' + j + '</a></li>');
                }
            }
            html.push('<li class="disabled hidden"><a href="JavaScript:;">...</a></li>');
            html.push('<li class="next"><a href="JavaScript:;">&raquo;</a></li></ul>');
            $(this).html(html.join(''));
            if (totalPage > showPage) $(this).find('ul.pagination li.next').prev().removeClass('hidden');

            var pageObj = $(this).find('ul.pagination'), preObj = pageObj.find('li.previous'),
                currentObj = pageObj.find('li').not('.previous,.disabled,.next'),
                nextObj = pageObj.find('li.next');

            function loopPageElement(minPage, maxPage) {
                var tempObj = preObj.next();
                for (var i = minPage; i <= maxPage; i++) {
                    if (minPage == 1 && (preObj.next().attr('class').indexOf('hidden')) < 0)
                        preObj.next().addClass('hidden');
                    else if (minPage > 1 && (preObj.next().attr('class').indexOf('hidden')) > 0)
                        preObj.next().removeClass('hidden');
                    if (maxPage == totalPage && (nextObj.prev().attr('class').indexOf('hidden')) < 0)
                        nextObj.prev().addClass('hidden');
                    else if (maxPage < totalPage && (nextObj.prev().attr('class').indexOf('hidden')) > 0)
                        nextObj.prev().removeClass('hidden');
                    var obj = tempObj.next().find('a');
                    if (!isNaN(obj.html()))obj.html(i);
                    tempObj = tempObj.next();
                }
            }

            function callBack(curr) {
                defaults.callback(curr, defaults.limit, totalCount);
            }

            currentObj.click(function (event) {
                event.preventDefault();
                var currPage = Number($(this).find('a').html()), activeObj = pageObj.find('li[class="active"]'),
                    activePage = Number(activeObj.find('a').html());
                if (currPage == activePage) return false;
                if (totalPage > showPage && currPage > 1)  {
                    var maxPage = currPage, minPage = 1;
                    if (($(this).prev().attr('class'))
                        && ($(this).prev().attr('class').indexOf('disabled')) >= 0) {
                        minPage = currPage - 1;
                        maxPage = minPage + showPage - 1;
                        loopPageElement(minPage, maxPage);
                    } else if (($(this).next().attr('class'))
                        && ($(this).next().attr('class').indexOf('disabled')) >= 0) {
                        if (totalPage - currPage >= 1) maxPage = currPage + 1;
                        else  maxPage = totalPage;
                        if (maxPage - showPage > 0) minPage = (maxPage - showPage) + 1;
                        loopPageElement(minPage, maxPage)
                    }                  
                }
                activeObj.removeClass('active');
                $.each(currentObj, function (index, thiz) {
                    if ($(thiz).find('a').html() == currPage) {
                        $(thiz).addClass('active');
                        callBack(currPage);
                    }
                });
            });
            preObj.click(function (event) {
                event.preventDefault();
                var activeObj = pageObj.find('li[class="active"]'), activePage = Number(activeObj.find('a').html());
                if (activePage <= 1) return false;
                if (totalPage > showPage) {
                    var maxPage = activePage, minPage = 1;                  
                    if ((activeObj.prev().prev().attr('class'))
                        && (activeObj.prev().prev().attr('class').indexOf('disabled')) >= 0) {
                        minPage = activePage - 1;
                        if (minPage > 1) minPage = minPage - 1;
                        maxPage = minPage + showPage - 1;
                        loopPageElement(minPage, maxPage);
                    }
                }
                $.each(currentObj, function (index, thiz) {
                    if ($(thiz).find('a').html() == (activePage - 1)) {
                        activeObj.removeClass('active');
                        $(thiz).addClass('active');
                        callBack(activePage - 1);
                    }
                });
            });
            nextObj.click(function (event) {
                event.preventDefault();
                var activeObj = pageObj.find('li[class="active"]'), activePage = Number(activeObj.find('a').html());
                if (activePage >= totalPage) return false;
                if (totalPage > showPage) {
                    var maxPage = activePage, minPage = 1;                  
                    if ((activeObj.next().next().attr('class'))
                        && (activeObj.next().next().attr('class').indexOf('disabled')) >= 0) {
                        maxPage = activePage + 2;
                        if (maxPage > totalPage) maxPage = totalPage;
                        minPage = maxPage - showPage + 1;
                        loopPageElement(minPage, maxPage);
                    }
                }
                $.each(currentObj, function (index, thiz) {
                    if ($(thiz).find('a').html() == (activePage + 1)) {
                        activeObj.removeClass('active');
                        $(thiz).addClass('active');
                        callBack(activePage + 1);
                    }
                });
            });
        }
    };
	/**按钮结束**/
    
    /**checkbox总开关**/
    function checkboxTotalSwitch(total){
    	$(total).unbind().change(function(){
    		if($(total).is(':checked')){
        		$(total).parents(".footable").find("td .i-checkboxs").prop("checked",true);
        	} else {
        		$(total).parents(".footable").find("td .i-checkboxs").prop("checked",false);
        	}
    	});
    }
    
    /*! checkbox分页脚本  button */
    function addRowsCheckbox(dataJson, type, tagType){
    	if(tagType=="checkbox" || tagType=="true"){
    		if(type=="th"){
    			var jsonCheckbox = [{"name":"checkbox",
    				"title":"<span class=\"glyphicon glyphicon-th\" style='font-size: 15px;color: #ccc;'></span>&nbsp;&nbsp;<input type=\"checkbox\" class=\"i-checks\" name=\"input[]\">",
    				"style":{"text-align": "right","width":60,"maxWidth":70}}];
    			return jsonCheckbox.concat(dataJson);
    		} else if (type=="td"){
    				var jsonCheckboxRow = [];
    				$.each(dataJson, function(i, obj){
    					obj.checkbox = "<input type=\"checkbox\" class=\"i-checks i-checkboxs\" name=\"input[]\" data-ubiety='"+i+"'>";
    					jsonCheckboxRow.push(obj)
    				});
//    			var row = $(".footable-toggle").parent(".footable-first-visible");
//    			row.html('<input type="checkbox" class="i-checks" name="input[]">');
    			return jsonCheckboxRow;
    		}
    	}
    	return dataJson;
    }

    //加table的父元素
    function addHtmlDiv($el, tagType){
    	if(tagType=="checkbox" || tagType=="true"){
    		$($el).find("th.footable-sortable.footable-first-visible").removeClass("footable-sortable");
    		$($el).find("th.footable-first-visible").find(".fooicon.fooicon-sort").remove();
    	}
    	if($($el).length>0){
    		
    		//给table加入父级标签
    		if($($el).parent(".ibox-content").length>0){
        		$($el).parent(".ibox-content").addClass("footable_box_content");
        	} else {
        		$($el).wrap("<div class='ibox-content footable_box_content'></div>");
        	}
    		
    		//给底部加按钮div
    		//$($el).parent(".ibox-content.footable_box_content").parents().css("height", "100%");
        	$($el).parent(".ibox-content.footable_box_content").after("<div class='footable_box_tfoot'></div>");
        	var countHeight = 0;//记录高度
        	var iBoxModelHeight = $($el).parents(".footable-ibox-model").height();//模板高度
    		var iBoxTitleHeight = $($el).parents(".footable-ibox-model").children(".ibox-title").innerHeight();//模板高度
    		var iBoxToolbar = $($el).parents(".footable-ibox-model").children(".ibox-toolbar");
    		if(iBoxToolbar.length>0){//计算高度
    			countHeight = iBoxModelHeight-iBoxTitleHeight-1-iBoxToolbar.innerHeight()-2-60;
    		} else {
    			countHeight = iBoxModelHeight-iBoxTitleHeight-2-60;
    		}
    		$($el).parent(".ibox-content.footable_box_content").css("height",countHeight+"px");
    	}
    	
    }
    
    /*! ajax分页脚本  button */
	$.fn.mtable = function (options, param) {
		var FTABLE = this;
		options = options || {};//是否赋值过,没赋值过则赋值{}
		if(typeof options == "string"){//是否是字符串
			return $.fn.mtable.methods[options](this, param);   
		} else {
			var jsonParam = {};
			options.paging = {"enabled":"false"};//关掉他们给的假分页功能
			
			var checkbox = options.checkbox;//是否启用CheckBox
			delete options["checkbox"]; 
			var columns = options.columns || [];//表格的属性
			if(columns.length>0){
				delete options["columns"]; 
				options.columns = addRowsCheckbox(columns, "th", checkbox);//开启CheckBox则添加CheckBox标签,反之则不添加
			}
			
			if(typeof options.url == 'undefined'){
				alert("url不能为空");
			} else {
				jsonParam.url = options.url;
				delete options["url"]; 
			}
//			jsonParam.async=false;	
			
			var pageSize;
			var index;
			if(typeof options.pageSize=='string' || typeof options.pageSize=='number'){
				pageSize = options.pageSize;
				delete options["pageSize"]; 
			} else {
				pageSize = 30;
			}
			if(typeof options.index=='string' || typeof options.index=='number'){
				index = options.index;
				delete options["index"]; 
			} else {
				index = 1;
			}		
			if(!options.notPage){
				if(options.data){
					jsonParam.data=options.data;
					if(typeof options.data.pageSize=='undefined'){
						jsonParam.data.rows=pageSize;
					}
					if(typeof options.data.index=='undefined'){
						jsonParam.data.page=index;
					}
				} else {
					jsonParam.data={"rows":pageSize, "page":index};
				}
			} else {
				jsonParam.data=options.data;
			}
			
			jsonParam.dataType="json";//返回值为json
			
			if(!options.type){
				jsonParam.type = "post";//默认提交方式为post
			} else {
				jsonParam.type = options.type;
				delete options["type"]; 
			}
			



		if(options.operSwitch) {//操作是否开启
			var operSwitchDiv = $("<div></div>");
			var methods=[];
			$.each(options.operSwitch, function(i, dom){
				var operDiv = $("<a href='JavaScript:;'></a>");
				if(i!=0){
					operDiv.append('&nbsp;&nbsp;');
				}
				if(dom.icon){
					operDiv.append('<i class="'+dom.icon+' the-icons-font"></i>');
				}
				if(dom.tip){
					operDiv.attr("title", dom.tip);
				}
				
				if(dom.text){
					operDiv.append(dom.text);
				}
				methods.push({
					method:dom.method,
					div:operDiv
				});
				operSwitchDiv.append(operDiv);
			});
			operSwitchDiv.width(25*(options.operSwitch.length));
			options.columns.push({"name":"操作","title":"操作",
				//"style":{"text-align":"center"},
				"formatter":function(value, options, rowData){
				var checkbox=rowData.checkbox;
				var index = $(checkbox).attr('data-ubiety');
				delete rowData["checkbox"]; 
				delete rowData["操作"]; 
				//rowData.checkbox
				
				$.each(methods,function(i,method){
					method.div.attr("onclick",method.method+"("+JSON.stringify(rowData)+","+index+")");
				});
        		return operSwitchDiv[0].outerHTML;},//用于将列单元格值转换为内容的函数。
        	});
		}

		var ft;
		var akey = getFootableClass(FTABLE);
		if(jsonCache[akey] && jsonCache[akey].ft){
			ft = jsonCache[akey].ft;
			if(FTABLE.find("th .i-checks").length>0){
				ft.$el.find("th .i-checks")[0].checked = false;
			}
			FTABLE.empty();
			FTABLE.attr("class","table");
		} 
		ft = FooTable.init(FTABLE,options);
				
//			var ft;
			jsonParam.success = function (result) {
				if(options.onLoadDataSuccess){//获得数据还未初始化加载
					var successResult= options.onLoadDataSuccess(result);
					if(successResult){
						result=successResult;
					}
				}
				ft.$el.parents(".ibox-content").next(".footable_box_tfoot").remove();//移除tfoot
				if(result.rows){
					if(!options.notPage){
						ft.rows.load(addRowsCheckbox(result.rows, "td", checkbox));
						//addRowsCheckbox(result.columns, "td", checkbox);
						checkboxTotalSwitch(FTABLE.find("th.footable-first-visible>.i-checks"))
						
						var colSize = result.total;//后台获取的数据总条数
						
						//var colspanNUM = FTABLE.find("th:visible").length;
						resultCache(FTABLE, result.rows, ft, jsonParam);//结果缓存
						
						addHtmlDiv(ft.$el, checkbox);//添加标签   计算高度
						
//						ft.$el.append($tfdiv);//添加tfoot
						
						ft.$el.parent(".ibox-content").next(".footable_box_tfoot").extendPagination({
				            totalCount: colSize,
				            showCount: pageSize,
				            limit: pageSize,
				            callback: function (curr, limit, totalCount) {
				            	
				            	var optionsChild = $.extend(true, {}, jsonParam);
				            	
				            	if(ft.reloadParam){
				            		if(ft.reloadParam.data){
				            			optionsChild.data=ft.reloadParam.data;
				            		}
				            		if(ft.reloadParam.url){
				            			optionsChild.url=ft.reloadParam.url;
				            		}
				            	}
				            	
				            	optionsChild.data.page = curr;
				            	
				            	optionsChild.success = function(dataResult){
				            		if(options.onLoadDataSuccess){//获得数据还未初始化加载
				    					var successResult= options.onLoadDataSuccess(dataResult);
				    					if(successResult){
				    						dataResult=successResult;
				    					}
				    				}
				            		ft.rows.load(addRowsCheckbox(dataResult.rows, "td", checkbox));
				            		resultCache(FTABLE, dataResult.rows, ft, jsonParam);//结果缓存
				            		
				            		if(options.onSuccess){//成功事件开发
				            			options.onSuccess(dataResult);
				    				}
				            	}
				            	optionsChild.error = function(jqXHR, textStatus, errorThrown){
				            		if(options.onError){
				    					options.onError(jqXHR, textStatus, errorThrown);
				    				}
				            	}
				            	$.ajax(optionsChild);
				            }

				        });
//						ft.$el.find("tfoot .footable-pagination-wrapper").extendPagination({
//				            totalCount: colSize,
//				            showCount: pageSize,
//				            limit: pageSize,
//				            callback: function (curr, limit, totalCount) {
//				            	var optionsChild = jsonParam;
//				            	jsonParam.data.index = curr;
//				            	optionsChild.success = function(dataResult){
//				            		ft.rows.load(addRowsCheckbox(dataResult.columns, "td", checkbox));
//				            		resultCache(FTABLE, dataResult.columns);//结果缓存
//				            	}
//				            	optionsChild.error = function(errorInfo){
//				            		alert("异常");
//				            	}
//				            	$.ajax(optionsChild);
//				            }
	//
//				        });
					}else{
						ft.rows.load(addRowsCheckbox(result.rows, "td", checkbox));
						checkboxTotalSwitch(FTABLE.find("th.footable-first-visible>.i-checks"));
						resultCache(FTABLE, result.rows, ft, jsonParam);//结果缓存
					}
				} else {
					ft.rows.load(addRowsCheckbox(result, "td", checkbox));
					checkboxTotalSwitch(FTABLE.find("th.footable-first-visible>.i-checks"));
					resultCache(FTABLE, result, ft, jsonParam);//结果缓存
				}
				
				if(options.onSuccess){
					options.onSuccess(result);
				}
			}
			jsonParam.error = function (jqXHR, textStatus, errorThrown) {
				if(options.onError){
					options.onError(jqXHR, textStatus, errorThrown);
				}
			};
			$.ajax(jsonParam);
		}
		
	}
	
	
	$.fn.mtable.methods = { options: function (jq) {//获取所有行数据
			var key = getFootableClass($(jq));
			return jsonCache[key].result;
	    }, getSelected: function(jq){//获取选中状态行数据
	    	var key = getFootableClass($(jq));
	    	var selectValue = [];
	    	$(jq).find('.i-checkboxs:checked').each(function(i, element){
	    		var index = $(element).data("ubiety");
	    		selectValue.push(jsonCache[key].result[index]);
	    	});
			return selectValue;
	    }, getSelections: function(jq){//获取所有行数据
	    	var key = getFootableClass($(jq));
	    	return jsonCache[key].result;
	    }, getSelectOne: function(jq){//获取选中行的第一条数据
	    	var key = getFootableClass($(jq));
	    	if($(jq).find('.i-checkboxs:checked').length>0){
	    		var i = $($(jq).find('.i-checkboxs:checked')[0]).data("ubiety");
	    		return jsonCache[key].result[i];
	    	} else {
	    		return ;
	    	}
	    		
	    }, reload: function(jq, param){
	    	var key = getFootableClass($(jq));
	    	var ft = jsonCache[key].ft;
	    	
	    	if(typeof param == 'object'){
	    		var ajaxParam = $.extend(true, {}, jsonCache[key].ajaxParam);
	    		ajaxParam.url = param.url;
	    		for(var key in param.data){
	    			ajaxParam.data[key] = param.data[key];
	    		} 
	    		ft.reloadParam= {
	    			data:ajaxParam.data,
	    			url:ajaxParam.url
	    		} 
	    		$.ajax(ajaxParam);
	    	}else{
	    		var ajaxParam = jsonCache[key].ajaxParam;
	    		ft.reloadParam=null;
		    	$.ajax(ajaxParam);
	    	}
	    }
    };  
	
	var jsonCache = {};
	function resultCache(label, result, ft, jsonParam){
		var key=$(label).attr('id');
		jsonCache[key] = {result:result};
		if(ft){
			jsonCache[key].ft = ft;
		}
		if(typeof jsonParam=='object'){
			jsonCache[key].ajaxParam = jsonParam;
		}
		/*for(var f=1; f<=$(".table").length; f++){
			if($(label).hasClass("footable-"+f)){
				var key="footable-"+f;
				jsonCache[key] = {};
				jsonCache[key].result = result;
				if(ft){
					jsonCache[key].ft = ft;
				}
				if(typeof jsonParam=='object'){
					jsonCache[key].ajaxParam = jsonParam;
				}
				break;
			}
		}*/
	}
	
	function getFootableClass(label){
		var key=$(label).attr('id');
		return key;
		/*for(var f=1; f<=$(".table").length; f++){
			if($(label).hasClass("footable-"+f)){
				return "footable-"+f;
			}
		}*/
	}
	
	// 工具栏添加
	$.fn.mboxtoolbar = function (params, tag) {
		if(params) {
			for(var key in params){
				if(key=="search"){
					var object = params[key];
					toobarHtmlBySearch(object, this);
				} else if (key=="button") {
					toobarHtmlByButton(object, this);
				}
			}
			if(tag && tag.onSuccess){
				tag.onSuccess();
			}
		}
	}
	
	//搜索组合
	function toobarHtmlBySearch(object, toolbar){
		var pdiv = "<div class='ibox-toolbar-div'></div>"
		var html = "<select class='form-control m-b ibox-ty-sty'></select>";
		
		var y = $("<div style='display: inline-block;'></div>");//父级div
		
		var T = $(html);
		/**
		 * 第一个选择框
		 */
		var texts = object.texts;
		$.each(texts, function(i, ele){
			if(typeof ele == 'object'){
				T.append("<option value='"+ele.value+"'>"+ele.text+"</option>");
			} else {
				T.append("<option>"+ele+"</option>");
			}
		});
		T.addClass("ibox-select-1");
		T.attr("name","msearch_name");
//		$(toolbar).append(T);
		T.wrap(pdiv);
		y.append(T.parent());
		
		/**
		 * 输入框
		 */
		var lable = '<input type="text" class="form-control ibox-ty-sty"/>';
		var LB = $(lable);
//		$(toolbar).append(LB);
		LB.attr("name","msearch_value");
		LB.wrap(pdiv);
		y.append(LB.parent());
		
		/**
		 * 第二个选择框
		 */
		if(object.sorts){
			var sorts = object.sorts;
			var S = $(html);
			$.each(sorts, function(i, ele){
				if(typeof ele == 'object'){
					S.append("<option value='"+ele.value+"'>"+ele.text+"</option>");
				} else {
					S.append("<option>"+ele+"</option>");
				}
			});
	//		$(toolbar).append(S);
			S.addClass("ibox-select-2");
			S.attr("name","msearch_sortName");
			S.wrap(pdiv);
			y.append(S.parent());
			
			/**
			 * 第三个选择框
			 */
			var orderby = $(html);
			orderby.append("<option value='asc'>升序</option>");
			orderby.append("<option value='desc'>降序</option>");
			orderby.addClass("ibox-select-3");
			orderby.attr("name","msearch_sortValue");
//			$(toolbar).append(orderby);
			orderby.wrap(pdiv);
			
			y.append(orderby.parent());
		}
		
		/**
		 * 按钮
		 */
		var butdiv = "<div class='ibox-toolbar-butdiv'></div>"
		var buthtml = '<button type="button" class="btn btn-primary btn-toolbar"><i class="fa fa-search"></i>&nbsp;查询</button>';
		var but = $(buthtml);
		if(typeof object.text=='string'){
			but.text(object.text);
		}
		
//		$(toolbar).append(but);
		but.attr("name","msearch_bottom");
		but.wrap(butdiv);
		y.append(but.parent());
		
		$(toolbar).prepend(y);
		
		but.click(function(){
			if(object.onClick){
				var obs = {};
				obs.name = T.val();
				obs.value = LB.val();
				if(S)
					obs.sortName = S.val();
				if(orderby)
					obs.sortValue = orderby.val();
				object.onClick(obs, but);
			}
		});
		
	}
})(jQuery);