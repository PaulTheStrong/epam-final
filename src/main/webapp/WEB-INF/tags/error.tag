<%@ attribute name="errorName" required="true" rtexprvalue="true" type="java.lang.String"   %>
<%@ attribute name="bundle" required="true" rtexprvalue="true" type="javax.servlet.jsp.jstl.fmt.LocalizationContext" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:if test="${errorName != null && errorName != ''}">
    <div class="error-box">
        <fmt:message bundle="${bundle}" key="${errorName}" />
    </div>
</c:if>
