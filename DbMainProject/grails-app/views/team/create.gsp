<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'team.label', default: 'Team')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-team" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-team" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.team}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.team}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.team}" method="POST">
        <fieldset class="form">
            <f:all bean="team"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>


<div id="create-department" class="content scaffold-create" role="main">
    <h1>Create Department</h1>


    %{--todo: if there's error, this doesn't show. change that--}%
    <iframe name="votar" style="display:none;" ></iframe>
    <form action="/department/save" method="post" target="votar">
        <fieldset class="form">
            <div class="fieldcontain required">
                <label for="name">Name
                    <span class="required-indicator">*</span>
                </label><input type="text" name="name" value="" required="" maxlength="40" id="name">
            </div>
        </fieldset>
        <fieldset class="buttons">
            <input type="submit" name="create" class="save" value="Create" id="create"
                   onclick="createDepartmentAndUpdate()">
        </fieldset>
    </form>
</div>
</body>
</html>
