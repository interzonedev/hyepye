<!DOCTYPE html>

<%@ page contentType="text/html; charset=utf-8" %>

<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en-US">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title>Hye Pye - Login</title>

        <link rel="icon" href="<c:url value="/assets/img/favicon.png" />" type="image/png" />

        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    </head>

    <body>
        <div class="container lead">
            <header>
                <div class="page-header">
                    <h1>Hye Pye - Login</h1>
                </div>
            </header>

            <form method="post" action="<c:url value="/login" />">
                <input type="hidden" name="remember" value="true" />
                <div class="row">
                    <c:if test="${param.loginError eq 'true'}">
                        <div class="col-xs-12">
                            <div class="form-group has-error">
                                Unrecognized login credentials.
                            </div>
                        </div>
                    </c:if>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label class="control-label" for="username">Username:</label>
                            <input class="form-control" type="text" id="username" name="username" />
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label class="control-label" for="password">Password:</label>
                            <input class="form-control" type="password" id="password" name="password" />
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <input type="submit" value="Login" class="btn btn-primary btn-xs" />
                    </div>
                </div>
            </form>
        </div>

        <script src="//code.jquery.com/jquery-2.1.0.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>
