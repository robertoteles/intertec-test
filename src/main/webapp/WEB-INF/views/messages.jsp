<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<mytags:style />
</head>
<body>
<mytags:menu />
<h2>Dirty Username Cleanser!</h2>
<c:if test="${not empty message}">
	<h3><c:out value="${message}"/></h3>
</c:if>
<mytags:footer/>
</body>
</html>
