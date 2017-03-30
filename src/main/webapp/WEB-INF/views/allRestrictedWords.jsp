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
<h3>Restricted Words</h3>
<c:choose>
	<c:when test="${fn:length(restrictedWords) gt 0}">
		<table border=1>
			<tr>
				<th>Name</th>
			</tr>
			<c:forEach var="restrictedWord" items="${restrictedWords}">
				<tr >
					<td><c:out value="${restrictedWord.word}"/></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>No Suggestions</c:otherwise>
</c:choose>
<mytags:footer/>

</body>
</html>
