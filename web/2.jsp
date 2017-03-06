<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Magnifier</title>
<style type="text/css">
#magnifier{
 width:342px;
 height:420px;
 position:absolute;
 top:100px;
 left:250px;
 font-size:0;
 border:1px solid #000;
}
#img{
 width:342px;
 height:420px;
}
#Browser{
 border:1px solid #000;
 z-index:100;
 position:absolute;
 background:#555;
}
#mag{
 border:1px solid #000;
 overflow:hidden;
 z-index:100;
}
</style>
<script type="text/javascript">
function getEventObject(W3CEvent) {   //�¼���׼������
 return W3CEvent || window.event;
}
function getPointerPosition(e) {   //��������������x,y��ú���
 e = e || getEventObject(e);
 var x = e.pageX || (e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft));
 var y = e.pageY || (e.clientY + (document.documentElement.scrollTop || document.body.scrollTop));
 
 return { 'x':x,'y':y };
}
function setOpacity(elem,level) {   //�������������͸��ֵ
 if(elem.filters) {
  elem.style.filter = 'alpha(opacity=' + level * 100 + ')';
 } else {
  elem.style.opacity = level;
 }
}
function css(elem,prop) {    //css���ú���,���Է�������cssֵ,���Ҽ�������͸��ֵ
 for(var i in prop) {
  if(i == 'opacity') {
   setOpacity(elem,prop[i]);
  } else {
   elem.style[i] = prop[i];
  }
 }
 return elem;
}
var magnifier = {
 m : null,
 
 init:function(magni){
  var m = this.m = magni || {
   cont : null,  //װ��ԭʼͼ���div
   img : null,   //�Ŵ��ͼ��
   mag : null,   //�Ŵ��
   scale : 15   //����ֵ,���õ�ֵԽ��Ŵ�Խ��,���������и���������������������ʱ,�����Щ��С�İױ�,Ŀǰ��֪����ν��
  }
  
  css(m.img,{ 
   'position' : 'absolute',
   'width' : (m.cont.clientWidth * m.scale) + 'px',    //ԭʼͼ��Ŀ�*����ֵ 
   'height' : (m.cont.clientHeight * m.scale) + 'px'    //ԭʼͼ��ĸ�*����ֵ
   })
  
  css(m.mag,{
   'display' : 'none',
   'width' : m.cont.clientWidth + 'px',   //m.contΪԭʼͼ��,��ԭʼͼ��ȿ�
   'height' : m.cont.clientHeight + 'px',
   'position' : 'absolute',
   'left' : m.cont.offsetLeft + m.cont.offsetWidth + 10 + 'px',  //�Ŵ���λ��Ϊԭʼͼ����ҷ�Զ10px
   'top' : m.cont.offsetTop + 'px'
   })
  
  var borderWid = m.cont.getElementsByTagName('div')[0].offsetWidth - m.cont.getElementsByTagName('div')[0].clientWidth;  //��ȡborder�Ŀ�
  
  css(m.cont.getElementsByTagName('div')[0],{   //m.cont.getElementsByTagName('div')[0]Ϊ�����
   'display' : 'none',        //��ʼ����Ϊ���ɼ�
   'width' : m.cont.clientWidth / m.scale - borderWid + 'px',   //ԭʼͼƬ�Ŀ�/����ֵ - border�Ŀ��
   'height' : m.cont.clientHeight / m.scale - borderWid + 'px',  //ԭʼͼƬ�ĸ�/����ֵ - border�Ŀ��
   'opacity' : 0.5     //����͸����
   })
  
  m.img.src = m.cont.getElementsByTagName('img')[0].src;   //��ԭʼͼ���srcֵ����Ŵ�ͼ��
  m.cont.style.cursor = 'crosshair';
  
  m.cont.onmouseover = magnifier.start;
  
 },
 
 start:function(e){
  
  if(document.all){    //ֻ��IE��ִ��,��Ҫ����IE6��select�޷�����
   magnifier.createIframe(magnifier.m.img);
  }
  
  this.onmousemove = magnifier.move;  //thisָ��m.cont
  this.onmouseout = magnifier.end;
 },
 
 move:function(e){
  var pos = getPointerPosition(e);  //�¼���׼��
  
  this.getElementsByTagName('div')[0].style.display = '';
  
  css(this.getElementsByTagName('div')[0],{
   'top' : Math.min(Math.max(pos.y - this.offsetTop - parseInt(this.getElementsByTagName('div')[0].style.height) / 2,0),this.clientHeight - this.getElementsByTagName('div')[0].offsetHeight) + 'px',
   'left' : Math.min(Math.max(pos.x - this.offsetLeft - parseInt(this.getElementsByTagName('div')[0].style.width) / 2,0),this.clientWidth - this.getElementsByTagName('div')[0].offsetWidth) + 'px'   //left=���x - this.offsetLeft - ������/2,Math.max��Math.min������򲻻ᳬ��ͼ��
   })
  
  magnifier.m.mag.style.display = '';
  
  css(magnifier.m.img,{
   'top' : - (parseInt(this.getElementsByTagName('div')[0].style.top) * magnifier.m.scale) + 'px',
   'left' : - (parseInt(this.getElementsByTagName('div')[0].style.left) * magnifier.m.scale) + 'px'
   })
  
 },
 
 end:function(e){
  this.getElementsByTagName('div')[0].style.display = 'none';
  magnifier.removeIframe(magnifier.m.img);  //����iframe
  
  magnifier.m.mag.style.display = 'none';
 },
 
 createIframe:function(elem){
  var layer = document.createElement('iframe');
  layer.tabIndex = '-1';
  layer.src = 'javascript:false;';
  elem.parentNode.appendChild(layer);
  
  layer.style.width = elem.offsetWidth + 'px';
  layer.style.height = elem.offsetHeight + 'px';
 },
 
 removeIframe:function(elem){
  var layers = elem.parentNode.getElementsByTagName('iframe');
  while(layers.length >0){
   layers[0].parentNode.removeChild(layers[0]);
  }
 }
}
window.onload = function(){
 magnifier.init({
       cont : document.getElementById('magnifier'),
       img : document.getElementById('magnifierImg'),
       mag : document.getElementById('mag'),
       scale : 3
       });
}
</script>
</head>
<body>
<div id="magnifier">
<img src="/images/friends.jpg" id="img" />
<div id="Browser"></div>
</div>
<div id="mag"><img id="magnifierImg" /></div>
<select style="position:absolute;top:200px;left:650px;width:100px;">
<option>select����</option>
<option>�Ƿ��ܸ���</option>
</select>
</body>
</html>