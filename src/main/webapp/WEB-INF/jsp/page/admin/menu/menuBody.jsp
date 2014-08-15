<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
    <nav>
        <ul class="nav nav-pills nav-stacked">
            <li><a href="<c:url value="/admin/metrics?pretty=true" />" title="Metrics">Metrics</a></li>
            <li><a href="<c:url value="/admin/healthcheck?pretty=true" />" title="Health Checks">Health Checks</a></li>
            <li><a href="<c:url value="/admin/vocabularyapp" />" title="Vocabulary Admin">Vocabulary Admin</a></li>
        </ul>
    </nav>
</div>
