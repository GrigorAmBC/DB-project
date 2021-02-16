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
<div>
    <div>
    </div>
    <form class="form" style="margin-left: 10px">
        <h2>Запрос с подзапросом</h2>

        Найти всех работников, которые ещё не принадлежат никакому отделу
        и при этом их зарплата выше зарплаты данного работника (параметр).
        <br>
        <g:select name="employee" from="${employees}"  value="${params.employee}" optionKey="id" required="" />
        <g:if test="${params.employee == null}" >
            <span style="color:red">Выберите работника и нажмите 'Обновить'</span>
        </g:if>
        <br>
        <input type="submit" value="Обновить">
    </form>

    <div id="list-employee" class="content scaffold-list" role="main">
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <f:table collection="${results}"/>
        <div class="pagination">
            <g:paginate total="${resultCount ?: 0}" />
        </div>
    </div>
</div>
</body>
</html>