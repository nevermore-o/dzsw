<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Demo</title>
</head>

<body style="width:2000px; height:1000px;">
    <div id="demo" style="position:absolute; left:518px; right:100px; width:500px; height:500px; background:#CC0000; top: 114px;">Demo为了方便就直接用绝对定位的元素</div>
</body>
</html>
<script>
document.getElementById('demo').onclick=function (){
        if (document.documentElement.getBoundingClientRect) { 
            alert("left:"+this.getBoundingClientRect().left)
            alert("top:"+this.getBoundingClientRect().top)
            alert("right:"+this.getBoundingClientRect().right)
            alert("bottom:"+this.getBoundingClientRect().bottom)
            var X= this.getBoundingClientRect().left+document.documentElement.scrollLeft;
            var Y = this.getBoundingClientRect().top+document.documentElement.scrollTop;
            alert("Demo的位置是X:"+X+";Y:"+Y)
        } 
}
</script>