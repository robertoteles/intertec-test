<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<html>
<head>
<mytags:jquery />
<mytags:style />
</head>
<body>
<mytags:menu/>
<h3>Usernames</h3>
<c:choose>
	<c:when test="${fn:length(usernames) gt 0}">
		<table border=1>
			<tr>
				<th>Name</th>
			</tr>
			<c:forEach var="username" items="${usernames}">
				<tr >
					<td><c:out value="${username.name}"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>No Suggestions</c:otherwise>
</c:choose>
<mytags:footer/>

</body>
</html>
