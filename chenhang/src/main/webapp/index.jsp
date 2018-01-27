<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Hello World!</h2>
	<%-- <%
		response.sendRedirect(request.getContextPath() + "/index/toMain");
	%> --%>
	<c:redirect url="${request.getContextPath() }/index/toMain" />
	<%-- <c:redirect url="/index/toMain" /> --%>
	<%-- <c:redirect url="index/toMain" /> --%>
</body>
</html>
