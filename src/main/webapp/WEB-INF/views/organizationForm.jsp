<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Denis">

    <title>Create organization</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script>
        function listEmpl(width, height, typeEmpl) {
            var l_url = "/listDirector?typeEmpl=" + typeEmpl;
            var leftPx = ( screen.availWidth - width ) / 2;
            var topPx = ( screen.availHeight - height ) / 2;
            var params = "width=" + width + ", height=" + height + ", top=" + topPx + ", left=" + leftPx;
            window.open(l_url, "_blank", params);
        }
        function deleteOrg(){
            window.location=("/deleteOrganization?id="+document.getElementsByName("id")[0].value);
        }

    </script>
</head>
<body>
<div class="container">


    <form:form method="POST" modelAttribute="organizationForm" class="form-signin">
        <div class="container">
            <c:if test="${id != null}">
                <button type="submit" class="btn btn-success">Save</button>
                <button type="button" class="btn btn-danger" onclick="deleteOrg()">Delete</button>
            </c:if>
            <c:if test="${id == null}">
                <button type="submit" class="btn btn-primary">Add</button>
            </c:if>
        </div>
        <input type="hidden" name="id" value="${id}">

        <%--<h2 class="form-signin-heading">${doit}</h2>--%>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="orgName" name="orgName" placeholder="OrgName" type="text" autofocus="true"
                   class="form-control" value="${organizationWrapper.organization.orgName}">
            <form:errors path="orgName"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="orgUrlAdress" name="orgUrlAdress" placeholder="Uridicheskiy Adress" type="text" autofocus="true"
                   class="form-control" value="${organizationWrapper.organization.orgUrlAdress}">
            <form:errors path="orgUrlAdress"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="orgAdress" name="orgAdress" placeholder="Organization Adress" type="text" autofocus="true"
                   class="form-control" value="${organizationWrapper.organization.orgAdress}">
            <form:errors path="orgAdress"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="orgTelephone" name="orgTelephone" placeholder="Organization Telephone" type="text"
                   autofocus="true"
                   class="form-control" value="${organizationWrapper.organization.orgTelephone}">
            <form:errors path="orgTelephone"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="orgEmail" name="orgEmail" placeholder="Organization email" type="text" autofocus="true"
                   class="form-control" value="${organizationWrapper.organization.orgEmail}">
            <form:errors path="orgEmail"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input type="hidden" name="directorId" value="${organizationWrapper.organization.semployee1.id}">
            <img src="${contextPath}/resources/image/add.gif" onclick="listEmpl(500, 600,1)">
            <input name="directorName" placeholder="Director" readonly type="text" autofocus="true" class="form-control"
                   value="${organizationWrapper.organization.semployee1.lastname} ${organizationWrapper.organization.semployee1.firstname} ${organizationWrapper.organization.semployee1.middlename}">
            <form:errors path="directorId"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input type="hidden" name="contactId" value="${organizationWrapper.organization.semployee2.id}">
            <img src="${contextPath}/resources/image/add.gif" onclick="listEmpl(500, 600,2)">
            <input name="contactName" placeholder="Employee contactor" readonly type="text" autofocus="true"
                   class="form-control"
                   value="${organizationWrapper.organization.semployee2.lastname} ${organizationWrapper.organization.semployee2.firstname} ${organizationWrapper.organization.semployee2.middlename}">
            <form:errors path="contactId"></form:errors>
        </div>
    </form:form>
</div>
</body>
</html>
