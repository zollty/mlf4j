/*!
 * @(#)waitingbox.js 
 *  A simple tool for waiting box
 * @athor zollty
 * @since 2013-11-10
 */
// ------------------------------------------------

// /////////////waiting box SART////////////////
var isWaitOnInit = false; //开关，如果设置为true，则会在文档加载时自动调用waiting box

// 在页面上Write一个DIV
document.write("<div id=\"waitingbox\" style=\"width:200px; height:60px; position:absolute; z-index:99; left:50%; top:50%; margin:-30px 0 0 -100px; border: 2px solid #CCE8CF; display:none;line-height: 60px; vertical-align: middle; text-align:center; z-index:100; font-size:12px; background-color:#F1FEDD; font-family:'微软雅黑';filter: alpha(opacity = 70);opacity: 0.7;\">正在处理中，请稍等 ...</div>");

var wb_div = document.getElementById("waitingbox");

function zoutyMouseHandle(event) {
	alert("处理未完成，请稍等 ...");
	event.cancelBubble = true;
}
function zoutyBlockMouseEvent() {
	if (document.all) {
		window.document.attachEvent('onmousedown', zoutyMouseHandle);
	} else {
		window.document.addEventListener('mousedown', zoutyMouseHandle, false);
	}
}
function zoutyReleaseMouseEvent() {
	if (document.all) {
		window.document.detachEvent('onmousedown', zoutyMouseHandle);
	} else {
		window.document.removeEventListener('mousedown', zoutyMouseHandle, false);
	}
}
function hideWBox() {
	zoutyReleaseMouseEvent();
	document.body.style.cursor = "";
	wb_div.style.display = "none";
}
function showWBox() {
	zoutyBlockMouseEvent();
	document.body.style.cursor = "wait";
	wb_div.style.display = "";
}

if(isWaitOnInit){
	showWBox();
	if (document.all){//IE
		window.attachEvent('onload',hideWaitingBox);
		window.attachEvent('onbeforeunload',showWaitingBox);
	}else{
		window.addEventListener('load',hideWaitingBox,false);
		window.addEventListener('beforeunload',showWaitingBox,false);
	}
}
///////////////waiting box END////////////////