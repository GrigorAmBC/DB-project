<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'queries.label', default: 'Query')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="list-department" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
</div>

<div style="margin-left: 40px">
    <div class="fieldcontain required">
        <a href="filteringAndSorting">Фильтры и сортировка</a>
    </div>
    <div>
        <a class="fieldcontain required" href="groupBy">Group by</a>
    </div>
    <div>
        <a class="fieldcontain required" href="having">Having</a>
    </div>
    <div>
        <a class="fieldcontain required" href="innerJoin">Inner Join</a>
    </div>
    <div>
        <a class="fieldcontain required" href="outerJoin">Outer Join</a>
    </div>
    <div>
        <a class="fieldcontain required" href="subQuery">Запрос с подзапросом</a>
    </div>
</div>
</body>
</html>