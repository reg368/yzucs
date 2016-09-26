<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style>
	label{
		width:150px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title></title>
</head>
<script>
	$(document).ready(function(){
		//#correctQNumber  是輸入答對幾題過關的 input type='number'
		//先判斷答對幾題過關參數值是否有設定
		var correctQNumber = '${level.correctQNumber}';
		if(correctQNumber == null || correctQNumber == ''){
			$("#correctQNumber").prop('disabled', true);
		}
		//監聽 答對幾題過關 checkbox 是否狀態有被點擊修改
		 $('#isDefaultCorrectNumber').change(function() {
		        if($(this).is(":checked")) {
		        	$("#correctQNumber").prop('disabled', true);
		        }else{
		        	$("#correctQNumber").prop('disabled', false);
		        }      
		    });
		
		
		//--------------------------------------------------------
		
		//假裝都預設
		 $("#fromQuestion").prop('disabled', true);
		 $("#toQuestion").prop('disabled', true);
		 $("#toQuestion option").remove();
		 $("#fromQuestion option").remove();
		 
		 
		 $('#isQRangeDefault').change(function() {
		        if($(this).is(":checked")) {
		        	$("#fromQuestion").prop('disabled', true);
		   		 	$("#toQuestion").prop('disabled', true);
		   		 	$("#toQuestion option").remove();
				 	$("#fromQuestion option").remove();
		        }else{
		        	$("#fromQuestion").prop('disabled', false);
		   		    $("#toQuestion").prop('disabled', false);
		   		 	
		   		    for(var i = 1 ; i <= 10 ; i ++){
		   		 	 	$("#fromQuestion").append($("<option></option>").attr("value", i).text(i));
		   		 		$("#toQuestion").append($("<option></option>").attr("value", i).text(i));
		   		 	}
		        	
		   		    
		        }      
		    });
		 
		 $('#fromQuestion').change(function() {
			 
		       var from = $( "#fromQuestion" ).val();  
		       $("#toQuestion option").remove();
		       
		       for(var i = from ; i <= 10 ; i ++){
		    	   $("#toQuestion").append($("<option></option>").attr("value", i).text(i));
		       }
		       
		       $('#toQuestion').val(10); 
		       
		    });
		 
	});
</script>
<body>
	<h2>修改關卡狀態 關卡 : ${level.l_level}</h2>
	<hr>
	<form method="post" action="*">
		<table>
			<tr>
				<td>開放狀態 &nbsp;&nbsp;</td>
				<td><input type="radio" name="isVisible" value="1" ${(level.isVisible == 0 )? '' : 'checked' } >開放&nbsp;</td>
				<td><input type="radio" name="isVisible" value="0" ${(level.isVisible == 0 )? 'checked' : '' } >不開放&nbsp;</td>			
			</tr>	
			<tr>
				<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>出題範圍 &nbsp;&nbsp;</td>
				<td>
					從第
					 <select id="fromQuestion">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
					  </select>
					題到第
					  <select id="toQuestion">
					  		<option value=""></option>
					  </select>	
					 題
				</td>
				<td><input type="checkbox" name="isQRangeDefault" id="isQRangeDefault" checked>預設全部</td>			
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
			</tr>	
			<tr>
				<td>出題順序 &nbsp;&nbsp;</td>
				<td><input type="radio" name="isRandom" value="1" ${(level.isRandom == 0 )? '' : 'checked' }>隨機&nbsp;</td>
				<td><input type="radio" name="isRandom" value="0" ${(level.isRandom == 0 )? 'checked' : '' }>不隨機&nbsp;</td>			
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>答對幾題過關 &nbsp;&nbsp;</td>
				<td><input type="number" name="correctQNumber" id="correctQNumber" value="${level.correctQNumber}" >&nbsp;&nbsp;</td>
				<td><input type="checkbox" name="" id="isDefaultCorrectNumber" ${level.correctQNumber == null ? 'checked' : ''}>預設全部</td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>過關獎勵金幣 &nbsp;&nbsp;</td>
				<td><input type="number" name="awardMoney" value="${level.awardMoney}" ></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td><td>&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td>過關獎勵經驗值 &nbsp;&nbsp;</td>
				<td><input type="number" name="awardExperience" value="${level.awardExperience}" ></td>
			</tr>
		</table> 
	</form>
</body>
</html>