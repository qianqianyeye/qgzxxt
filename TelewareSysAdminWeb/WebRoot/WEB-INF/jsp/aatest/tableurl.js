
jQuery(function($){
	
	//工具栏
	$("#toolbar").mboxtoolbar({
		"search":{
			texts:[{text:"xx",value:'dd'}],//第一个选择框
			sorts:["ff","dd"],//第二个选择框(存在该参数的时候,第三个选择框同时出现)
			//text:"搜索",//text默认为查询
			onClick:function(value, but){//点击事件的回调方法, 第一个参数为按钮前面的输入框的值
				
			}
		},
	},{
		onSuccess:function(){//控件加载后回调方法,  不用的这个方法时可以不写
			
		}
	})
	
	//表格
	$('#accordion-example-23').mtable({
		"url":"hadmin/hindexjson2",
		"type":"post",
		"checkbox":"checkbox",//显示checkbox标签,不显示则false或省略
		//"sorting":true,
		"sorting":{
			"enabled": true,//开启排序插件, 非必填
		},
		//"showHeader":false,//不显示标题行,显示则为true;非必填
		"showToggle":true,//列隐藏时是否显示行切换
		"breakpoints":{ "xs": 480, "sm": 768, "md": 992, "lg": 1200 },//该对象定义了列用于确定可见性的断点
		"cascade":false,//更改断点如何影响列可见性。
		
		"operSwitch":[{//该属性是开启操作列的 对象
			icon:'fa fa-pencil-square-o', //操作列的i标签的class
//			text:'修改',//非必填
			method:"update",//填写方法名,再在自己的页面或脚本上写个function sss(arg1, arg2),第一个参数是本行的所有数据,第二个参数是所在行的位置
		},{
			icon:'fa fa-trash-o the-icons-red',
			//text:'删除',
			method:"del",
		}],//操作开关, 修改和删除
		"columns": [//该属性是用来标记
					{
						"name":"id",//包含此列单元格值的行对象上的属性名称。
						"title":"ID",//列的标题是与其单元格的值相关联的友好名称。
						"breakpoints":"xs sm md lg",//一个以空格分隔的断点名称字符串，指定何时应该隐藏这些列单元格并将其显示在详细信息行中
						"style":{"width":80,"maxWidth":80},//用于定义本列中所有单元格的内联样式的对象
						"classes": "nnn mmm ddd",//一个空格分隔的CSS类的字符串应用于该行
						"parser":function(result){
							//console.log(this.$el);//浏览器控制台打印 value
							//console.log(result); //浏览器控制台打印 result
							return result;
						}, //用于从DOM或其他值检索单元格值的函数。
					},
    	            {
						"name":"firstName","title":"First Name",
						"type":"string"//该值为string,如果你type写为number,属性对不上则内容不能显示
						
					},
    	            {
						"name":"lastName",
						"title":"Last Name",
    	            	"formatter":function(value, options, rowData){
    	            		//console.log(this.$el);//浏览器控制台打印 dom
    	            		//console.log(value);//浏览器控制台打印 value
    	            		//console.log(options);//浏览器控制台打印 options
    	            		//console.log(rowData);//浏览器控制台打印 rowData
    	            		return "pop";},//用于将列单元格值转换为内容的函数。
    	            },
    	            {
    	            	"name":"something",
    	            	"title":"Never seen but always around",
    	            	"visible":true//该列被永久隐藏
    	            },
    	            {"name":"jobTitle","title":"Job Title","breakpoints":"xs sm","style":{"maxWidth":200,"overflow":"hidden","textOverflow":"ellipsis","wordBreak":"keep-all","whiteSpace":"nowrap"}},
    	            {"name":"started","title":"Started On","type":"date","breakpoints":"xs sm md","formatString":"MMM YYYY"},
    	            {"name":"dob","title":"Date of Birth","type":"date","breakpoints":"xs sm md","formatString":"DD MMM YYYY"},
    	            {"name":"status","title":"Status"},
    
    	            ],
		"onSuccess":function(){//table加载成功后的回调函数
		}
	});
	
	
	
	
});

function cc(){
	$('#accordion-example-23').mtable("getSelectOne");//获取CheckBox选中的的第一行数据
	$('#accordion-example-23').mtable("getSelected");//获取选中状态行数据
	$('#accordion-example-23').mtable("getSelections");//获取所有行数据
	$('#accordion-example-23').mtable("reload");//刷新table
	$('#accordion-example-23').mtable("reload",{//重新访问地址,并刷新加载
		url:"xxx/ddd",
		data:{
			ddd:"xx",
			yyy:"dd"
		}
	});//
	
}

function update(row, index){
}

function del(row, index){
}
