<%@page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(function(){
	SCH();
	
	//반영
	function SCH(){
		var param={"ORD_NO":1,"CLASS_CD":"1","CLASS_NM":"1","CLASS_TYPE_CD":"1"};
		
		$('#crudTable > tbody:last').empty(); //계속추가되지 않도록 초기화하고 조회
		
		$.ajax({
			url:"/select",
			type:"POST",
			dataType:'json',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				console.log(data);
				for(var i=0;i<data.length;i++){
						$('#crudTable > tbody:last').append('<tr><td style="height:27px; text-align:center;">'
								+data[i].ORD_NO+'</td><td style="height:27px; text-align:left;">'
								+data[i].CLASS_CD+'</td><td style="height:27px; text-align:left;">'
								+data[i].CLASS_NM+'</td><td style="height:27px; text-align:left;">'
								+data[i].CLASS_TYPE_CD+'</td></tr>');
					
				}
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});

	}
	
	
	$("#select").click(function(){
		SCH();
	});
	$("#insert").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/insert",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("추가완료");
				SCH();
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	$("#update").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/update",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("수정완료");
				SCH();
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	$("#delete").click(function(){
		$.ajax({
			type: "post",
			async:false,
			url:"/delete",
			dataType:'text',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(null),
			success: function(data){ 
				alert("삭제완료");
				SCH();
			},
			error: function (xhr, ajaxOptions, thrownError) { 
				alert(xhr.status+' Error');
			}  
		});
	});
	
	
	
	
	
	
});
</script>


<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	공법 
</h1>

<div align="right" style="padding-right: 100px;">
	<input type="button" id="select" value="조회"/> 
	<input type="button" id="insert" value="추가"/> 
	<input type="button" id="update" value="수정"/> 
	<input type="button" id="delete" value="삭제"/>
</div>
 
<!-- 그리드 -->
<div style="padding-top: 20px;">
	<table id="crudTable" style="width:500px;" border="1">
		<colgroup>
			<col width="20%"><col width="30%"><col width="30%"><col width="20%">
		</colgroup> 
		<tr>
			<th bgcolor="#B2EBF4"><label style="padding-top:5px; height: 29px; font-size: 20px;">순번</label></th>
			<th bgcolor="#B2EBF4"><label style="padding-top:5px; height: 29px; font-size: 20px;">공법코드</label></th>
			<th bgcolor="#B2EBF4"><label style="padding-top:5px; height: 29px; font-size: 20px;">공법명</label></th>
			<th bgcolor="#B2EBF4"><label style="padding-top:5px; height: 29px; font-size: 20px;">구분</label></th>
		</tr>
		<tbody></tbody>
	</table>
</div> 

	




</body>
</html>
