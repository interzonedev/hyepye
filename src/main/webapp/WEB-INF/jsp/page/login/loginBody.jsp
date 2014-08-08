<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form method="post" action="<c:url value="/login" />" class="form-horizontal" role="form">
    <%--<input type="hidden" name="remember" value="true" />--%>
    <c:if test="${param.loginError eq 'true'}">
        <div class="col-sm-12">
            <div class="alert alert-danger">
                Unrecognized login credentials.
            </div>
        </div>
    </c:if>
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">Username</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" type="text" id="username" name="username" placeholder="Username">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="remember" value="true" checked="checked" /> Remember me
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Login</button>
        </div>
    </div>
</form>
