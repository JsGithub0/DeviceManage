<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<table border="1" cellpadding="0" cellspacing="0" align="center">
    <c:forEach begin="1" end="9" var="number">
        <tr>
            <c:forEach begin="1" end="9" var="number1">
                <td width="60" align="center">
                    <c:out value="${number}*${number1}=${number*number1}"></c:out>
                </td>
            </c:forEach><br>
        </tr>
    </c:forEach><br>
</table>
</body>
</html>
