<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.model.UserVO" %>
<% UserVO uservo = (UserVO) request.getAttribute("UserVO"); %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />	
  <meta name="GCD" content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJl7a8d9faff24b1dba7e2264cb50684898"/>
  <meta charset="utf-8">
  <title>選擇角色</title>
  <meta name="generator" content="Google Web Designer 1.6.0.0429">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdgesture_style.css" rel="stylesheet" data-version="3" data-exports-type="gwd-gesture">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdimage_style.css" rel="stylesheet" data-version="7" data-exports-type="gwd-image">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdpage_style.css" rel="stylesheet" data-version="8" data-exports-type="gwd-page">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdgallerynavigation_style.css" rel="stylesheet" data-version="4" data-exports-type="gwd-gallerynavigation">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdswipegallery_style.css" rel="stylesheet" data-version="14" data-exports-type="gwd-swipegallery">
	
    <style type="text/css" id="gwd-text-style">p {
    margin: 0px;
}
h1 {
    margin: 0px;
}
h2 {
    margin: 0px;
}
h3 {
    margin: 0px;
}</style>
  <style type="text/css">html,
body {
    width: 100%;
    height: 100%;
    margin: 0px;
}
body {
    transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    -webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    -moz-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    perspective: 1400px;
    -webkit-perspective: 1400px;
    -moz-perspective: 1400px;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    background-color: transparent;
}
.gwd-img-1j4a {
    position: absolute;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    left: 2px;
    top: 2px;
    transform-origin: 609.869px 188.594px 0px;
    -webkit-transform-origin: 609.869px 188.594px 0px;
    -moz-transform-origin: 609.869px 188.594px 0px;
    height: 100%;
    width: 100%;
}
.gwd-div-ilnq {
    position: absolute;
    border-radius: 3px;
    top: 72px;
    left: 2.03%;
    height: 82.91%;
    width: 32.66%;
    transform-origin: 145px 211.085px 0px;
    -webkit-transform-origin: 145px 211.085px 0px;
    -moz-transform-origin: 145px 211.085px 0px;
    background-color: rgb(197, 186, 193);
}
.gwd-input-1puq {
    left: -258px;
    top: 499px;
}
.gwd-input-1f7k {} .gwd-input-pd36 {
    position: absolute;
    width: 356.25px;
    height: 36.25px;
    left: 288.25px;
    top: 180.75px;
    border-radius: 3px;
}
.gwd-input-132i {
    left: 0px;
}
.gwd-input-qy9j {
    top: 0px;
}
.gwd-input-1uwv {} .gwd-input-1j2i {} .gwd-form-14az {
    left: -54px;
    top: 259px;
}
.gwd-button-r673 {
    position: absolute;
    border-radius: 3px;
    border-image-source: none;
    border-color: rgb(226, 230, 231);
    height: 35px;
    width: 7.88%;
    transform-origin: 35.0494px 17.3474px 0px;
    -webkit-transform-origin: 35.0494px 17.3474px 0px;
    -moz-transform-origin: 35.0494px 17.3474px 0px;
    top: 369px;
    left: 4%;
    background-image: none;
    background-color: rgb(98, 206, 224);
}
.gwd-p-lswn {
    position: absolute;
    width: 268px;
    height: 88px;
    left: 3%;
    top: 4100px;
    color: red;
}
.gwd-swipegallery-algo {
    position: absolute;
    top: 245px;
    height: 283px;
    left: 49.32%;
    width: 323px;
    transform-origin: 161.5px 141.5px 0px;
    -webkit-transform-origin: 161.5px 141.5px 0px;
    -moz-transform-origin: 161.5px 141.5px 0px;
}
.gwd-p-xfrq {
    position: absolute;
    width: 126.667px;
    height: 27.8333px;
    font-weight: bold;
    color: blue;
    top: 86px;
    left: 4%;
}
.gwd-p-17d8 {
    position: absolute;
    color: red;
    font-weight: bold;
    width: 432px;
    left: 45.05%;
    top: 480px;
    height: 86px;
    transform-origin: 216px 43px 0px;
    -webkit-transform-origin: 216px 43px 0px;
    -moz-transform-origin: 216px 43px 0px;
}
.gwd-button-4l0e {
    top: 83px;
    left: 4.05%;
    width: 26.58%;
    transform-origin: 119.363px 17.3438px 0px;
    -webkit-transform-origin: 119.363px 17.3438px 0px;
    -moz-transform-origin: 119.363px 17.3438px 0px;
}
.gwd-button-1k6i {
    background-color: rgb(98, 155, 224);
}
.gwd-button-5gf7 {
    left: 4%;
    top: 189px;
}
.gwd-button-n0nc {
    left: 4%;
    top: 2400px;
}
.gwd-button-sfoc {
    left: 4%;
    top: 291px;
}
.gwd-button-ndms {
    left: 4%;
    top: 129px;
}
.gwd-gallerynavigation-15zl {
    position: absolute;
    width: 257px;
    height: 30px;
    border-style: none;
    transform-origin: 117px 14px 0px;
    -webkit-transform-origin: 117px 14px 0px;
    -moz-transform-origin: 117px 14px 0px;
    left: 53%;
    top: 368px;
    background-image: none;
}
.character_image {
    position: absolute;
    top: 40%;
    height: 50%;
    left: 50%;
    width: 20%;
    transform-origin: 161.5px 141.5px 0px;
    -webkit-transform-origin: 161.5px 141.5px 0px;
    -moz-transform-origin: 161.5px 141.5px 0px;
	}
.pet_image {
    position: absolute;
    top: 40%;
    height: 25%;
    left: 80%;
    width: 15%;
    transform-origin: 161.5px 141.5px 0px;
    -webkit-transform-origin: 161.5px 141.5px 0px;
    -moz-transform-origin: 161.5px 141.5px 0px;
	}

</style>

<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/googbase_min.js" data-version="3" data-exports-type="googbase" src="<%= request.getContextPath()%>/javascript_css/addUser/googbase_min.js">
</script>
<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwd_webcomponents_min.js" data-version="5" data-exports-type="gwd_webcomponents" src="<%= request.getContextPath()%>/javascript_css/addUser/gwd_webcomponents_min.js">
</script>
<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdgesture_min.js" data-version="3" data-exports-type="gwd-gesture" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdgesture_min.js"></script>
<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdimage_min.js" data-version="7" data-exports-type="gwd-image" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdimage_min.js">
</script>
<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdpage_min.js" data-version="8" data-exports-type="gwd-page" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdpage_min.js">
</script>
<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdgallerynavigation_min.js" data-version="4" data-exports-type="gwd-gallerynavigation" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdgallerynavigation_min.js">
</script>
<script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdswipegallery_min.js" data-version="14" data-exports-type="gwd-swipegallery" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdswipegallery_min.js">
</script>
<script type="text/javascript" gwd-events="support" src="<%= request.getContextPath()%>/javascript_css/addUser/gwd-events-support.1.0.js">
</script>
<!-- jquery -->
<script src="<%=request.getContextPath()%>/javascript_css/jquery.min.js"></script>
   
   <script type="text/javascript" gwd-events="handlers">
        window.gwd = window.gwd || {};
        gwd.onCharacterSelected = function(event) {
          var id = event.detail.id;
          document.getElementById('char_index').value = id;
        };
        gwd.onPetSelected = function(event) {
          var id = event.detail.id;
          //alert('pet index ' + id + ' selected');
        };
   </script>
   <script type="text/javascript" gwd-events="registration">
        // Google Web Designer 中處理事件的支援程式碼
         // 這個指令碼區塊是自動產生的，請勿編輯！
        gwd.actions.events.registerEventHandlers = function(event) {
          gwd.actions.events.addHandler('gwd-swipegallery_1', 'frameshown', gwd.onCharacterSelected, false);
        };
        gwd.actions.events.deregisterEventHandlers = function(event) {
          gwd.actions.events.removeHandler('gwd-swipegallery_1', 'frameshown', gwd.onCharacterSelected, false);
        };
        document.addEventListener("DOMContentLoaded", gwd.actions.events.registerEventHandlers);
        document.addEventListener("unload", gwd.actions.events.deregisterEventHandlers);
  </script> 	
  
</head>
<body>
  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/background/background_login.png" style="">
  <div class="gwd-div-ilnq" style=""></div>
  
    <form method="post" action="<%=request.getContextPath()%>">
    	<button type="submit" class="gwd-button-r673" style="top: 450px;">修改密碼</button>
    	<input type="hidden" name="action" value="character_selected">
    	<input type="hidden" name="char_index" id="char_index"  >
    </form> 
   
    <c:choose>
    	<c:when test="${not empty groups }">
   			<c:forEach var="group" items="${groups}" varStatus="loop">
     			<form method="post" action="<%= request.getContextPath()%>/front/question/QuestionServlet.do">
     				<button  type="submit" class="gwd-button-r673 gwd-button-4l0e gwd-button-1k6i " style="left: 4%; top: ${ (loop.index * 35) + (loop.index * 10) + 129 }px;">${group.g_name}</button>
     				<input type="hidden" name="g_id" value="${group.g_id}">
     				<input type="hidden" name="action" value="startgame">
     			</form>
     		</c:forEach>	
    	</c:when>
    	<c:otherwise>
    		<font color="red" class="gwd-button-r673 gwd-button-4l0e gwd-button-1k6i"  style="left:4%; top:30%;" >目前沒有課程 , 請聯絡您的課程導師</font>
    	</c:otherwise>
    </c:choose>
   
  
  <p class="gwd-p-lswn" style="top:500px;">
  		<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li>${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
  </p>
  
  <p class="gwd-p-xfrq" style="">請選擇課程</p>
  <!--  <p class="gwd-p-17d8" style="">description</p> -->
  <img class="character_image" style="" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_character_image}">
  <img class="pet_image" style="" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_pet_image}">
  
  
</body>
</html>