<jsp:include page="../../../template/main.jsp">
    <jsp:param name="title" value="Vocabulary Admin" />
    <jsp:param name="pageHeader" value="Vocabulary Admin" />
    <jsp:param name="bodyContent" value="../page/admin/vocabulary/appBody.jsp" />
    <jsp:param name="bodyJsIncludes" value="../page/admin/vocabulary/appJsIncludes.jsp" />
    <jsp:param name="includeCsrfJs" value="${true}" />
</jsp:include>
