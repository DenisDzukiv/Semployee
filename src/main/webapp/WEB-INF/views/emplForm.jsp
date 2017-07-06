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

        <title>${doit}</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script>
        function blockData() {
            var value = document.getElementsByName("typeEmployeeId")[0].value;

            if (value == 2) {
                document.getElementById("workPhone").style.display = "none";
                document.getElementById("org").style.display = "none";
            }
            else {
                document.getElementById("workPhone").style.display = "";
                document.getElementById("org").style.display = "";
            }

        }
        function deleteEmpl(){
            window.location=("/deleteSemployee?id="+document.getElementsByName("id")[0].value);
        }

    </script>

</head>

<body>

<div class="container">


    <form:form method="POST" modelAttribute="emplForm" class="form-signin">

        <div class="container">
            <c:if test="${id != null}">
            <button type="submit" class="btn btn-success">Save</button>
                <button type="button" class="btn btn-danger" onclick="deleteEmpl()">Delete</button>
            </c:if>
            <c:if test="${id == null}">
                <button type="submit" class="btn btn-primary">Add</button>
            </c:if>
         </div>
        <input type="hidden" name="id" value=${id}>
        <input type="hidden" name="valid" value=${valid}>

        <%--<h2 class="form-signin-heading">${doit}</h2>--%>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="lastname" name="lastname" placeholder="Lastname" type="text" autofocus="true"
                   class="form-control" value="${semployeeWrapper.semployee.lastname}">

            <form:errors path="lastname"></form:errors>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="firstname" name="firstname" placeholder="Firstname" type="text" autofocus="true"
                   class="form-control" value="${semployeeWrapper.semployee.firstname}">
            <form:errors path="firstname"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="middlename" name="middlename" placeholder="Middlename" type="text" autofocus="true"
                   class="form-control" value="${semployeeWrapper.semployee.middlename}">
            <form:errors path="middlename"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="email" name="email" placeholder="Email" type="text" autofocus="true" class="form-control"
                   value="${semployeeWrapper.semployee.email}">
            <form:errors path="email"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="emplTelephone" name="emplTelephone" placeholder="EmplTelephone" type="text" autofocus="true"
                   class="form-control" value="${semployeeWrapper.semployee.emplTelephone}">
            <form:errors path="emplTelephone"></form:errors>
        </div>
        <div class="form-group">
            <select class="form-control" id="typeEmployeeId" name="typeEmployeeId" onchange="blockData()">
                <c:forEach var="row" items="${typeEmployeesMap}" varStatus="loop">
                    <option value="${row.key}">${row.value}</option>
                </c:forEach>

            </select>
            <form:errors path="typeEmployeeId"></form:errors>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="emplTelephoneWork" name="emplTelephoneWork" placeholder="EmplTelephoneWork" type="text"
                   autofocus="true" class="form-control" value="${semployeeWrapper.semployee.emplTelephoneWork}">
            <form:errors path="emplTelephoneWork"></form:errors>
        </div>
        <div class="form-group">
            <select class="form-control" id="orgId" name="orgId">
                <option value=""></option>
                <c:forEach var="row" items="${organizationMap}" varStatus="loop">
                    <option value="${row.key}">${row.value}</option>
                </c:forEach>
            </select>
            <form:errors path="orgId"></form:errors>
        </div>
        <%--<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>--%>
    </form:form>
</div>
<script>
    if (document.getElementsByName("id")[0].value != "" || document.getElementsByName("valid")[0].value == "validate") {
        var select = document.getElementsByName("typeEmployeeId")[0].value = "${semployeeWrapper.semployee.typeEmployee.id}";
        var select = document.getElementsByName("orgId")[0].value = "${semployeeWrapper.semployee.organization3.id}";
    }
</script>
</body>
</html>
