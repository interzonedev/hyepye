<!DOCTYPE html>

<%@ page contentType="text/html; charset=utf-8" %>

<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="excludeAppJs" value="${false}" />
<c:if test="${(not empty param.excludeAppJs) and (param.excludeAppJs)}">
    <c:set var="excludeAppJs" value="${true}" />
</c:if>

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
