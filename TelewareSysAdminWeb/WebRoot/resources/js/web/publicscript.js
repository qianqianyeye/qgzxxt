/**
 * 这里编写公共方法使用的脚本方法内容
 * @returns {String}
 */

/**
 * 获取请求地址的到项目名称的内容的地址路径
 */
function getProjectURL(){  
    var curWwwPath = window.document.location.href;  
    //获取主机地址之后的目录，如： cis/website/meun.htm  
    var pathName = window.document.location.pathname;  
    var pos = curWwwPath.indexOf(pathName); //获取主机地址，如： http://localhost:8080  
    var localhostPaht = curWwwPath.substring(0, pos); //获取带"/"的项目名，如：/cis  
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);  
    var rootPath = localhostPaht +"/";
    return rootPath;  
      
} 
