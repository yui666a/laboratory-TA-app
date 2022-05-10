<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ja" />
<head>
<meta charset="UTF-8" />
<%
	String pcId = (String)session.getAttribute("pcId");
	String lastName = (String)session.getAttribute("lastName");
	String firstName = (String)session.getAttribute("firstName");
	Boolean handStatus = (Boolean)session.getAttribute("handStatus");
%>
</head>
<body>
	PCID：<%=pcId%> <br>
	姓：<%=lastName%> <br>
	名：<%=firstName%> <br>
	挙手状態：<%=handStatus%> <br>

	<script type="text/javascript" src="axios@0.24.0.js"></script>
	<script type="text/javascript" src="vue-router@3.5.3.js"></script>
	<script type="text/javascript" src="vue@3.1.5.js"></script>
</body>
</html>
