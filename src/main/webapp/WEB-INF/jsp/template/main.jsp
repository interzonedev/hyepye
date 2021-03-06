<!DOCTYPE html>

<%@ page contentType="text/html; charset=utf-8" %>

<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="excludeAppJs" value="${false}" scope="page" />
<c:if test="${(not empty param.excludeAppJs) and (param.excludeAppJs)}">
    <c:set var="excludeAppJs" value="${true}" scope="page" />
</c:if>

<%-- Display the logout form if the user is authenticated and is not on the login page. --%>
<c:set var="displayLogoutForm" value="${false}" scope="page" />
<sec:authorize access="isAuthenticated()">
    <c:if test="${not fn:contains(pageContext.request.servletPath, 'login')}">
        <c:set var="displayLogoutForm" value="${true}" scope="page" />
        <c:url var="logoutFormAction" value="/logout" scope="page" />
    </c:if>
</sec:authorize>

<html lang="en-US">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title>Hye Pye - ${param.title}</title>

        <link rel="icon" href="<c:url value="/assets/img/favicon.png" />" type="image/png" />

        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />

        <c:if test="${not empty param.cssIncludes}">
            <jsp:include page="${param.cssIncludes}" />
        </c:if>

        <c:if test="${!excludeAppJs}">
            <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.22/angular.min.js"></script>
        </c:if>

        <c:if test="${not empty param.headJsIncludes}">
            <jsp:include page="${param.headJsIncludes}" />
        </c:if>
    </head>

    <body>
        <div class="container lead">
            <header>
                <div class="page-header">
                    <h1>Hye Pye - ${param.pageHeader}</h1>
                    <c:if test="${displayLogoutForm}">
                        <form method="post" action="${logoutFormAction}">
                            <sec:csrfInput />
                            <button type="submit" class="btn btn-xs">Logout</button>
                        </form>
                    </c:if>
                </div>
            </header>

            <jsp:include page="${param.bodyContent}" />
        </div>

        <c:if test="${!excludeAppJs}">
            <script src="//code.jquery.com/jquery-2.1.1.min.js"></script>
            <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
            <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.22/angular-resource.min.js"></script>
            <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.22/angular-route.min.js"></script>
            <script src="<c:url value="/assets/js/app/izng.js" />"></script>

            <%--
                Add a map of Spring Security CSRF token properties to the base izng object if specified and if CSRF is
                enabled.
            --%>
            <c:if test="${(param.includeCsrfJs) and (not empty _csrf)}">
                <script>
                    (function(context) {
                        context.izng.csrf = {
                            "token": "${_csrf.token}",
                            "headerName": "${_csrf.headerName}",
                            "parameterName": "${_csrf.parameterName}"
                        };
                    }(this));
                </script>
            </c:if>

            <c:if test="${not empty param.bodyJsIncludes}">
                <jsp:include page="${param.bodyJsIncludes}" />
            </c:if>
        </c:if>
    </body>
</html>
