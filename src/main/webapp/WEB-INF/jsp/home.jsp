<!DOCTYPE html>

<%@ page contentType="text/html; charset=utf-8" %>

<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en-US">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title>Hye Pye - Welcome</title>

        <link rel="icon" href="<c:url value="/assets/img/favicon.png" />" type="image/png" />

        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    </head>

    <body>
        <div class="container lead">
            <header>
                <div class="page-header">
                    <h1>Hye Pye - Welcome</h1>
                </div>
            </header>
        </div>

        <script src="//code.jquery.com/jquery-2.1.0.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular-resource.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular-sanitize.min.js"></script>
        <script src="<c:url value="/assets/js/app/izng.js" />"></script>
    </body>
</html>
