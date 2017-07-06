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
        function listEmpl(width, height, typeEmpl){
            var l_url = "/listDirector?typeEmpl=" + typeEmpl;
            var leftPx = ( screen.availWidth - width ) / 2;
            var topPx = ( screen.availHeight - height ) / 2;
            var params = "width=" +width+ ", height=" +height+ ", top=" +topPx+ ", left=" +leftPx;
            window.open(l_url, "_blank", params);
        }

    </script>
</head>
<body>
<div class="container">


    <form:form method="POST" modelAttribute="organizationForm" class="form-signin">

        <h2 class="form-signin-heading">Create organization</h2>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="orgName" class="form-control" placeholder="Name"
                        autofocus="true"></form:input>
            <form:errors path="orgName"></form:errors>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="orgUrlAdress" class="form-control" placeholder="Uridicheskiy Adress"
                        autofocus="true"></form:input>
            <form:errors path="orgUrlAdress"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="orgAdress" class="form-control" placeholder="Organization Adress"
                        autofocus="true"></form:input>
            <form:errors path="orgAdress"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="orgTelephone" class="form-control" placeholder="Organization Telephone"
                        autofocus="true"></form:input>
            <form:errors path="orgTelephone"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="orgEmail" class="form-control" placeholder="Organization email"
                        autofocus="true"></form:input>
            <form:errors path="orgEmail"></form:errors>
        </div>


        <div class="form-group ">
            <input type="hidden" name="directorId" value="">
            <img src="${contextPath}/resources/image/add.gif" onclick="listEmpl(500, 600,1)">
            <input name="directorName" placeholder="Director" readonly type="text" autofocus="true" class="form-control" value="">
        </div>
        <div class="form-group ">
            <input type="hidden" name="contactId" value="">
            <img src="${contextPath}/resources/image/add.gif" onclick="listEmpl(500, 600,2)">
            <input name="contactName" placeholder="Employee contactor" readonly type="text" autofocus="true" class="form-control" value="">
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>
