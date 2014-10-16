/*!
 * @(#)WebPathUtils.js 
 *  Utils for web path
 * @athor zollty 
 * @since 2013-11-10
 */
// ------------------------------------------------

if (typeof WebPathUtils !== 'object') {
	WebPathUtils = {};
}

(function () {
    'use strict';
    var g_RootPath='';
    /** 
     * WebPathUtils.getRootPath()
     *  获取网站的根路径，如：http://localhost:8080/xxx/
     */
    if (typeof WebPathUtils.getRootPath !== 'function') {
    	WebPathUtils.getRootPath = function () {
    		if(g_RootPath==''){
    		    //获取当前网址，如： http://localhost:8080/xxx/meun.jsp
    		    var curWwwPath=window.document.location.href;
    		    var pos = curWwwPath.indexOf('?');
    		    if(pos!=-1){
    		    	curWwwPath = curWwwPath.substring(0, pos);
    		    }
    		    //获取主机地址之后的目录，如： /xxx/meun.jsp
    		    var pathName=window.document.location.pathname;
    		    //截取网站项目的根路径，如：http://localhost:8080/xxx/
    		    g_RootPath = curWwwPath.substr(0, curWwwPath.length-pathName.length+pathName.substr(1).indexOf('/')+2);
    	    }
    	    return g_RootPath;
    	};
    }
}());