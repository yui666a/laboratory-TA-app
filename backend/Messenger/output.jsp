<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ja" />
<head>
<meta charset="UTF-8" />
<%
	String pcIpAddress = (String)request.getAttribute("pcIpAddress");
	String pcId = (String)request.getAttribute("pcId");
	Boolean handStatus = (Boolean)request.getAttribute("handStatus");
	Boolean helpStatus = (Boolean)request.getAttribute("helpStatus");
%>
</head>
<body>
	IPAddress：<%=pcIpAddress %><br>
	PCID：<%=pcId%> <br>
	挙手状態：<%=handStatus%> <br>
	TAヘルプ状態：<%=helpStatus%><br>

	<script type="text/javascript" src="axios@0.24.0.js"></script>
	<script type="text/javascript" src="vue-router@3.5.3.js"></script>
	<script type="text/javascript" src="vue@3.1.5.js"></script>
	
	<form method="get" action="MainServlet">
		<div>挙手テスト</div>
		<input type="submit" value=
		<% if(handStatus){ %>
			"解決済"
		<% }else{ %>
			"挙手"
		<% } %>
		/>
	</form>
	
	<form method="get" action="whoami">
		<input type="submit" value="whoami">
	</form>
</body>
</html>
