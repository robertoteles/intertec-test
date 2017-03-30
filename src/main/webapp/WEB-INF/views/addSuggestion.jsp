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
<h4>Suggestion Availables</h4>
<c:if test="${not empty result.message}">
	<h3><c:out value="${result.message}"/></h3>
</c:if>
<c:if test="${fn:length(result.usernames) gt 0}">
	<table>
	<c:forEach var="suggestion" items="${result.usernames}">
				 <tr >
					<td><a href="addSuggestion.htm?suggestion=<c:out value="${suggestion.name}"/>"><c:out value="${suggestion.name}"/></a></td>
				</tr>
	</c:forEach>
	</table>
</c:if>
<mytags:footer/>
</body>
</html>
