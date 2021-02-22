<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body onload="initFields()">
<a href="#list-team" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-team" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${teamList}"/>

    <div class="pagination">
        <g:paginate total="${teamCount ?: 0}"/>
    </div>
</div>



<form action="index" method="get" name="filters" id="filters">
    <fieldset class="form">
        <div class="fieldcontain required">
            <h4 class="fieldcontain required">Фильтры</h4>
        </div>
        <div class="fieldcontain required">
            <label for="fromDate">Created from</label>
            <input type="date" id="fromDate" name="fromDate" placeholder="from" value="${params.fromDate}"/>
        </div>

        <div class="fieldcontain required">
            <label for="toDate">Created to</label>
            <input type="date" id="toDate" name="toDate" placeholder="to" value="${params.toDate}"/>
        </div>

        <div class="fieldcontain required">
            <label for="name">Name</label>
            <input type="text" name="name" maxlength="50" id="name" value="${params.name}">
        </div>

        <div class="fieldcontain required">
            <label for="fromBudget">Budget from</label>
            <input type="number" name="fromBudget" min="0"
                   max="10000000" id="fromBudget" value="${params.fromBudget}">
        </div>

        <div class="fieldcontain required">
            <label for="toBudget">Budget to</label>
            <input type="number" name="toBudget" value="${params.toBudget}"
                   min="0" max="10000000" id="toBudget">
        </div>

        <div class="fieldcontain required">
            <label for="department">Department</label>
            <select name="department" id="department">
                <option value="-1"></option>
            </select>
        </div>

        <div class="fieldcontain">
            <label for="working">Is Working</label>
            <select name="working" id="working">
                <option value="-1"></option>
                <option value="true">True</option>
                <option value="false">False</option>
            </select>
        </div>

        <div class="fieldcontain">
            <input type="button" name="create" class="save" value="Применить" id="create" onclick="validateAndSend()">
            <input type="button" name="reset" value="Сбросить" onclick="resetFilterForm()">
        </div>
    </fieldset>
</form>
<script>
    function resetFilterForm() {
        document.querySelector('#name').value = ""
        document.querySelector('#fromBudget').value = ""
        document.querySelector('#toBudget').value = ""
        document.querySelector('#department').value = ""
        document.querySelector('#working').value = ""
        document.querySelector('#fromDate').value = ""
        document.querySelector('#toDate').value = ""
    }

    function validateAndSend() {
        const filters = document.querySelector('#filters')
        const toBudget = filters.toBudget.value
        const fromBudget = filters.fromBudget.value
        if (toBudget !== '' && fromBudget !== '' && parseInt(toBudget) < parseInt(fromBudget)) {
            alert('\'Budget from\' должен быть меньше \'Budget to\'!')
            return false
        }

        const fromDate = document.querySelector('#fromDate').value
        const toDate = document.querySelector('#toDate').value
        if (fromDate !== '' && toDate !== '') {
            var d1 = new Date(fromDate);
            var d2 = new Date(toDate);
            if (d1.getTime() > d2.getTime()) {
                alert('Created from должен быть раньше, чем Created to!')
                return false
            }
        }

        filters.submit();
    }
</script>
<script>
    setTimeout(initFields, 10)

    function initFields() {
        const http = new XMLHttpRequest();
        const url = "http://localhost:8080/department.json"
        http.open("GET", url);

        http.send();
        http.onreadystatechange = (e) => {
            console.log(http.response)
            const departmentSelector = document.querySelector('#department')
            if (departmentSelector.options.length > 1) {
                return
            }

            const length = departmentSelector.options.length
            const departments = JSON.parse(http.response)

            departments.forEach(function (value) {
                var opt = document.createElement('option');
                opt.value = value.id;
                opt.innerHTML = value.name;
                departmentSelector.appendChild(opt);
            })
            initDepartment()
        }
    }

    function getUrlVars() {
        var vars = {};
        var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
            vars[key] = value;
        });
        return vars;
    }

    function initDepartment() {
        const departmentSelector = document.querySelector('#department')
        var department = getUrlVars()["department"]
        if (department === undefined) {
            department = -1
        }


        for (var i = 0, len = departmentSelector.options.length; i < len; i++) {
            var opt = departmentSelector.options[i];
            if (opt.value.toString() === department.toString()) {
                console.log(i)
                departmentSelector.selectedIndex = i
                break
            }
        }
    }

    function initWorking() {
        const workingSelector = document.querySelector('#working')
        var working = getUrlVars()["working"]
        if (working === undefined) {
            working = -1
        }

        for (var i = 0, len = workingSelector.options.length; i < len; i++) {
            var opt = workingSelector.options[i];
            if (opt.value.toString() === working.toString()) {
                console.log(i)
                workingSelector.selectedIndex = i
                break
            }
        }
    }

    initWorking()
</script>
</body>
</html>