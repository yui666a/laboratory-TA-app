<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="ja" />
<head>
<meta charset="UTF-8" />
<%
	String pcIpAddress = (String)request.getAttribute("pcIpAddress");
	String pcIdwithICS = (String)request.getAttribute("pcId");
	int beginIdx = pcIdwithICS.indexOf("8");
	String pcId = pcIdwithICS.substring(beginIdx);
	
	Boolean handStatus = (Boolean)request.getAttribute("handStatus");
	Boolean helpStatus = (Boolean)request.getAttribute("helpStatus");
	
	String myPc = (String)request.getAttribute("myPc");
	String pcList = (String)request.getAttribute("pcList");
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
	
	<form method="get" action="/Messenger/v1/whoami">
		<input type="submit" value="whoami">
	</form>
	
	<form method="get" action="/Messenger/v1/active-seats">
		<input type="submit" value="active-seats">
	</form>
	<form method="post" action="/Messenger/v1/call/<%=pcId%>">
		<input type="submit" value="call-teacher">
	</form>
	
	myPc:<%=myPc %> <br>
	pcList:<%=pcList %>
</body>
</html>
