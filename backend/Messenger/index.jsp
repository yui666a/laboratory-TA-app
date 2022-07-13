<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>座席状態</title>
    <script src="./vue-router@3.5.3.js"></script>
    <script src="./vue@3.1.5.js"></script>
    <!-- <script src="https://unpkg.com/vue@3"></script> -->
    <link href="style.css" rel="stylesheet" />
    
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
  	<%=pcIpAddress %>
  	<%=pcId %>
  	<%=handStatus %>
  	<%=helpStatus %>
  	<br>
    <div id="app"></div>
    <script type="module" src="./script.js"></script>
  </body>
</html>
