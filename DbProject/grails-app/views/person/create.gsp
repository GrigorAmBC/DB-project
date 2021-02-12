<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-person" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><a href="/gender/create" class="create">New Gender</a></li>
    </ul>
</div>

<div id="create-person" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.person}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.person}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.person}" method="POST">
        <fieldset class="form" id="person-fieldset">
            <f:all bean="person"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>

<div>
    <div id="create-gender" class="content scaffold-create" role="main">
        <h1>Create Gender</h1>

        <iframe name="votar" style="display:none;" ></iframe>
        <form action="/genders.json" method="post" target="votar" id="some_form">
            <fieldset class="form">
                <div class="fieldcontain required">
                    <label for="name">Name
                        <span class="required-indicator">*</span>
                    </label><input type="text" name="name" value="" required="" maxlength="15" id="name">
                </div>
            </fieldset>
            <fieldset class="buttons">
                <input type="submit" value="Create" id="create"
                       onclick="createGenderAndUpdate()">
            </fieldset>
        </form>

    </div>
    %{--<h1>Create gender</h1>
        <form action="/gender/create" method="post">
            <fieldset class="form">
                <div class="fieldcontain required">
                    <label for="name">Name
                        <span class="required-indicator">*</span>
                    </label><input type="text" name="name" value="" required="" maxlength="20" id="name">
                </div><div class="fieldcontain required">
            </div>
            </fieldset>
            <fieldset class="buttons">
                <input type="submit" name="create" class="save" value="Create" id="create">
            </fieldset>
        </form>

    <g:form url="[controller: 'gender', action: 'create']">
        < property="Gender" />
        <g:textField name="Name" value="sdlkfs" prefix="sldk"/>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>--}%
</div>

</body>

</html>