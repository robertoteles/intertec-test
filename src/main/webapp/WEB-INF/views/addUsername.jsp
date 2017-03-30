<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<html>
<head>
<mytags:jquery />
<mytags:style />
</head>
<body>
<mytags:menu />
<h4>New Username</h4>
<c:if test="${not empty result.message}">
	<h3><c:out value="${result.message}"/></h3>
</c:if>
<form method="post" action="addUsername.htm"><label for="name">Username:</label><input
	type="text" name="name" /><br />
<input type="submit" /><input type="reset" /></form>
<mytags:footer/>
</body>
</html>
