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
  <title>index</title>
  <meta name="generator" content="Google Web Designer 1.6.0.0429">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdgesture_style.css" rel="stylesheet" data-version="3" data-exports-type="gwd-gesture">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdimage_style.css" rel="stylesheet" data-version="7" data-exports-type="gwd-image">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdpage_style.css" rel="stylesheet" data-version="8" data-exports-type="gwd-page">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdgallerynavigation_style.css" rel="stylesheet" data-version="4" data-exports-type="gwd-gallerynavigation">
  <link href="<%= request.getContextPath()%>/javascript_css/addUser/gwdswipegallery_style.css" rel="stylesheet" data-version="14" data-exports-type="gwd-swipegallery">
  <style type="text/css" id="gwd-text-style">
  	p{margin:0px}h1{margin:0px}h2{margin:0px}h3{margin:0px}
  </style>
  <style type="text/css">
  	html,body{
  		width:100%;
  		height:100%;
  		margin:0px
  	}
  	body{
  		transform:matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  		-webkit-transform:matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  		-moz-transform:matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
  		perspective:1400px;-webkit-perspective:1400px;
  		-moz-perspective:1400px;transform-style:preserve-3d;
  		-webkit-transform-style:preserve-3d;
  		-moz-transform-style:preserve-3d;
  		background-color:transparent
  	}
  	.gwd-img-1j4a{
  		position:absolute;transform-style:preserve-3d;
  		-webkit-transform-style:preserve-3d;
  		-moz-transform-style:preserve-3d;
  		left:2px;top:2px;transform-origin:609.869px 188.594px 0px;
  		-webkit-transform-origin:609.869px 188.594px 0px;
  		-moz-transform-origin:609.869px 188.594px 0px;
  		height:100%;width:100%
  	}
  	.gwd-div-ilnq{
  		position:absolute;
  		border-radius:3px;top:22px;
  		left:2.03%;
  		height:85.91%;
  		width:32.66%;
  		transform-origin:145px 211.085px 0px;
  		-webkit-transform-origin:145px 211.085px 0px;
  		-moz-transform-origin:145px 211.085px 0px;
  		background-color:#c5bac1
  		}
    .gwd-input-1puq{
  		left:-258px;
  		top:449px
  		}
  	.gwd-input-pd36{
  		position:absolute;
  		width:356.25px;
  		height:36.25px;
  		left:288.25px;
  		top:130.75px;border-radius:3px
  	 }
  	 .gwd-input-132i{
  	 	left:0px
  	 }
  	 .gwd-input-qy9j{
  	 	top:0px
  	 }
  	 .gwd-input-gbnv{
  	 	position:absolute;
  	 	border-radius:3px;
  	 	height:30px;
  	 	top:41px;
  	 	left:3.04%;
  	 	width:29.73%;
  	 	transform-origin:137.323px 21.75px 0px;
  	 	-webkit-transform-origin:137.323px 21.75px 0px;
  	 	-moz-transform-origin:137.323px 21.75px 0px
  	 }
  	 .gwd-form-14az{left:1px;top:1px}.gwd-input-imcr{position:absolute;border-radius:3px;transform-origin:211px 17.4598px 0px;-webkit-transform-origin:211px 17.4598px 0px;-moz-transform-origin:211px 17.4598px 0px;height:30px;left:27%;width:46%;top:180px;transform-style:preserve-3d;-webkit-transform-style:preserve-3d;-moz-transform-style:preserve-3d}.gwd-button-r673{position:absolute;border-radius:3px;border-image-source:none;border-color:#e2e6e7;height:35px;width:7.88%;transform-origin:35.0494px 17.3474px 0px;-webkit-transform-origin:35.0494px 17.3474px 0px;-moz-transform-origin:35.0494px 17.3474px 0px;left:3%;top:319px;background-image:none;background-color:#62cee0}.gwd-input-140p{top:88px;left:3.04%;width:29.73%;transform-origin:137.252px 17.4531px 0px;-webkit-transform-origin:137.252px 17.4531px 0px;-moz-transform-origin:137.252px 17.4531px 0px}.gwd-input-ho4o{top:135px;left:3.04%;width:29.73%;transform-origin:137.252px 17.4531px 0px;-webkit-transform-origin:137.252px 17.4531px 0px;-moz-transform-origin:137.252px 17.4531px 0px}.gwd-input-e1q9{left:3%;top:192px}.gwd-input-1uj3{position:absolute;width:44px;height:40.5px;left:28px;top:244px}.gwd-input-1cp5{left:156px;top:244px}.gwd-p-19lj{position:absolute;width:48px;height:41px;left:77px;top:247px;font-weight:bold;color:black}.gwd-p-k3cq{width:21px;height:18px;transform-origin:10.5px 9px 0px;-webkit-transform-origin:10.5px 9px 0px;-moz-transform-origin:10.5px 9px 0px;top:255px;left:223px}.gwd-p-1w90{width:22px;height:20px;transform-origin:11px 10.25px 0px;-webkit-transform-origin:11px 10.25px 0px;-moz-transform-origin:11px 10.25px 0px;top:255px;left:95px}.gwd-input-xlvn{left:28px;top:244px}.gwd-p-lswn{position:absolute;width:268px;height:88px;left:3%;top:360px;color:red}.gwd-swipegallery-algo{position:absolute;width:256px;height:238px;transform-origin:128px 119px 0px;-webkit-transform-origin:128px 119px 0px;-moz-transform-origin:128px 119px 0px;left:560px;top:59px}.gwd-swipegallery-dx9g{position:absolute;height:238px;width:249px;transform-origin:124.5px 119px 0px;-webkit-transform-origin:124.5px 119px 0px;-moz-transform-origin:124.5px 119px 0px;left:878px;top:59px}.gwd-p-xfrq{position:absolute;width:126.667px;height:27.8333px;left:631px;top:13px;font-weight:bold;color:blue}.gwd-p-65eu{left:943px}.gwd-p-17d8{position:absolute;width:241px;height:184px;left:575px;top:311px;color:blue;font-weight:bold}.gwd-p-1wnj{left:879px}</style>
  <script data-source="<%= request.getContextPath()%>/javascript_css/addUser/googbase_min.js" data-version="3" data-exports-type="googbase" src="<%= request.getContextPath()%>/javascript_css/addUser/googbase_min.js"></script>
  <script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwd_webcomponents_min.js" data-version="5" data-exports-type="gwd_webcomponents" src="<%= request.getContextPath()%>/javascript_css/addUser/gwd_webcomponents_min.js"></script>
  <script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdgesture_min.js" data-version="3" data-exports-type="gwd-gesture" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdgesture_min.js"></script><script data-source="gwdimage_min.js" data-version="7" data-exports-type="gwd-image" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdimage_min.js"></script>
  <script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdpage_min.js" data-version="8" data-exports-type="gwd-page" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdpage_min.js"></script>
  <script data-source="gwdgallerynavigation_min.js" data-version="4" data-exports-type="gwd-gallerynavigation" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdgallerynavigation_min.js"></script>
  <script data-source="<%= request.getContextPath()%>/javascript_css/addUser/gwdswipegallery_min.js" data-version="14" data-exports-type="gwd-swipegallery" src="<%= request.getContextPath()%>/javascript_css/addUser/gwdswipegallery_min.js"></script>
  <script type="text/javascript" gwd-events="support" src="<%= request.getContextPath()%>/javascript_css/addUser/gwd-events-support.1.0.js"></script>
  <script type="text/javascript" gwd-events="support" src="<%= request.getContextPath()%>/javascript_css/addUser/gwd-events-support.1.0.js"></script>
  <script type="text/javascript" gwd-events="handlers">
  	window.gwd=window.gwd||{};
  	gwd.onCharacterSelected=function(event){
  		var id=event.detail.id;
  		var format = '.jpg';
  		if(id == '1'){
  			document.getElementById("c_description").innerHTML = '姓名:大頭<br><br>考艾島人，血型O型，生日9月11日，對杏仁過敏，在棒球隊中擔任左外場手。一個沒有什麼主見，容易受人影響但很善良的男孩。';
  		}else if(id == '2'){
  			document.getElementById("c_description").innerHTML = '姓名:艾瑞克<br><br>傲慢自大且玩世不恭，四賤客中腦袋最聰明，卻常把聰明才智用錯地方，被譽為鎮上最邪惡的小孩，他的反猶情緒經常讓他與凱子發生衝突。';
  		}else if(id == '3'){
  			document.getElementById("c_description").innerHTML = '姓名:凱子<br><br>四人組中的猶太人，常與艾瑞克發生衝突，但有時會受艾瑞克影響一起鬧事，當他受影響時常會鬧出天大的災難。';
  		}else if(id == '4'){
  			document.getElementById("c_description").innerHTML = '姓名:屎蛋<br><br>主角群中唯一的正常人，早期看到溫蒂或其他有好感的女性會嘔吐。在10歲時曾被診斷出有亞斯伯格，雖然後面看開了，但偶爾還是會借酒澆愁。';
  		}else if(id == '5'){
  			document.getElementById("c_description").innerHTML = '姓名:女英雄<br><br>';
  			format = '.png';
  		}
  		
  		var context_path =  document.getElementById("context_path").value;
  	    document.getElementById("user_character_image").value = context_path+'/images/addUser/character_'+id+format;
  	};
  	gwd.onPetSelected=function(event){
  		var id=event.detail.id;
  		var context_path =  document.getElementById("context_path").value;
  		var format = '.jpg';
  		if(id == '1'){
  			document.getElementById("p_description").innerHTML = '姓名:假面大頭王<br><br>';
  		}else if(id == '2'){
  			document.getElementById("p_description").innerHTML = '姓名:浣熊俠<br><br>';
  		}else if(id == '3'){
  			document.getElementById("p_description").innerHTML = '姓名:風箏人<br><br>';
  		}else if(id == '4'){
  			document.getElementById("p_description").innerHTML = '姓名:工具人<br><br>';
  		}else if(id == '5'){
  			document.getElementById("p_description").innerHTML = '';
  		}	
  	    document.getElementById("user_pet_image").value = context_path+'/images/addUser/pet_'+id+format;
  	}
  </script>
  <script type="text/javascript" gwd-events="registration">
  	gwd.actions.events.registerEventHandlers=function(event){
  		gwd.actions.events.addHandler("gwd-swipegallery_1","frameshown",gwd.onCharacterSelected,false);
  		gwd.actions.events.addHandler("gwd-swipegallery_2","frameshown",gwd.onPetSelected,false)
  	};
  		gwd.actions.events.deregisterEventHandlers=function(event){
  			gwd.actions.events.removeHandler("gwd-swipegallery_1","frameshown",gwd.onCharacterSelected,false);
  			gwd.actions.events.removeHandler("gwd-swipegallery_2","frameshown",gwd.onPetSelected,false)
  	};
  			document.addEventListener("DOMContentLoaded",gwd.actions.events.registerEventHandlers);
  			document.addEventListener("unload",gwd.actions.events.deregisterEventHandlers);
  </script>	
</head>

<body>
  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/main_background.jpg" style="">
  <div class="gwd-div-ilnq" style=""></div>
  <input type="hidden" id="context_path" value="<%=request.getContextPath()%>">
  
  
  <form action="<%= request.getContextPath()%>/front/user/UserServlet.do" method="post"  name="registerForm"  class="gwd-form-14az" id="character_image_gallary" >
    <input type="text" name="user_login_id" value="<%= (uservo==null) ? "" : uservo.getUser_login_id() %>" class="gwd-input-gbnv" style="" placeholder="請輸入帳號">
    <input type="password" name="user_password" value="<%= (uservo==null) ? "" : uservo.getUser_password() %>" class="gwd-input-imcr gwd-input-140p" style="" placeholder="請輸入密碼">
    <input type="text" name="user_name" value="<%= (uservo==null) ? "" : uservo.getUser_name() %>" class="gwd-input-imcr gwd-input-ho4o" style="" placeholder="請輸入角色名稱">
    <input type="text" name="user_pet_name" value="<%= (uservo==null) ? "" : uservo.getUser_pet_name() %>" class="gwd-input-imcr gwd-input-ho4o gwd-input-e1q9" style="" placeholder="請輸入寵物名稱">
    <input name="user_gender" value="man" class="gwd-input-1uj3 gwd-input-xlvn" type="radio" style="" <%= (uservo==null) ? "" : ("man".equals(uservo.getUser_gender()))? "checked" : "" %>>
    <input name="user_gender" value="female" class="gwd-input-1uj3 gwd-input-1cp5" type="radio" style="" <%= (uservo==null) ? "" : ("female".equals(uservo.getUser_gender()))? "checked" : "" %>>
    <input type="hidden" id="user_character_image" name="user_character_image" value="">
    <input type="hidden" id="user_pet_image" name="user_pet_image" value="">
    <input type="hidden" value="registerForm" id="action" name="action">
    <button type="submit"  class="gwd-button-r673" style="">完成送出</button>
  </form>
  
  <!-- error message -->
  <div class="gwd-p-lswn" style="">
  	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li>${message}</li>
			</c:forEach>
			<li style="color:blue;">新增帳號失敗 , 請重新選擇角色和寵物</li>	
		</ul>	
	</c:if>
  </div> 
  
  
  <p class="gwd-p-19lj gwd-p-1w90" style="">男</p>
  <p class="gwd-p-19lj gwd-p-k3cq" style="">女</p>
  
  <gwd-swipegallery id="gwd-swipegallery_1" scaling="contain" class="gwd-swipegallery-algo" images="<%=request.getContextPath()%>/images/addUser/character_1.jpg,<%=request.getContextPath()%>/images/addUser/character_2.jpg,<%=request.getContextPath()%>/images/addUser/character_3.jpg,<%=request.getContextPath()%>/images/addUser/character_4.jpg,<%=request.getContextPath()%>/images/addUser/character_5.png"></gwd-swipegallery>
  <gwd-swipegallery id="gwd-swipegallery_2" scaling="contain" class="gwd-swipegallery-dx9g" images="<%=request.getContextPath()%>/images/addUser/pet_1.jpg,<%=request.getContextPath()%>/images/addUser/pet_2.jpg,<%=request.getContextPath()%>/images/addUser/pet_3.jpg,<%=request.getContextPath()%>/images/addUser/pet_4.jpg"></gwd-swipegallery>
  <p class="gwd-p-xfrq" style="">請選擇角色</p>
  <p class="gwd-p-xfrq gwd-p-65eu" style="">請選擇寵物</p>
  <div class="gwd-p-17d8" style="" id="c_description">character descripton</div>
  <div class="gwd-p-17d8 gwd-p-1wnj" style="" id="p_description">pet description</div>

</body>
</html>