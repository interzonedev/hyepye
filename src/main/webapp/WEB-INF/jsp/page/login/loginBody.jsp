<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="<c:url value="/login" />" role="form">
    <input type="hidden" name="remember" value="true" />
    <div class="row">
        <c:if test="${param.loginError eq 'true'}">
            <div class="col-xs-12">
                <div class="alert alert-danger">
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
