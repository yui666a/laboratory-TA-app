<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ja" />
<head>
<meta charset="UTF-8" />
<%
	String pcId = (String)session.getAttribute("pcId");
	Boolean isStudent = (Boolean)session.getAttribute("isStudent");
	Boolean handStatus = (Boolean)session.getAttribute("handStatus");
	Boolean helpStatus = (Boolean)session.getAttribute("helpStatus");
%>
</head>
<body>
	<h2>TA教員用ページ</h2>
	PCID：<%=pcId%> <br>
<%-- 	姓：<%=lastName%> <br>
	名：<%=firstName%> <br> --%>
	挙手状態：<%=handStatus%> <br>
	TAヘルプ状態：<%=helpStatus%><br>

	<script type="text/javascript" src="axios@0.24.0.js"></script>
	<script type="text/javascript" src="vue-router@3.5.3.js"></script>
	<script type="text/javascript" src="vue@3.1.5.js"></script>
</body>
</html>
