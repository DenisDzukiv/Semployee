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

    <title>Create employee</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <script>
        function blockData(){
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
        function isEmailAddress(theElement, theElementName)
        {
            var s = theElement.value;
            var localPartfilter1 = /^[^<>()\[\]\x5C.,;:@" ]+(\.[^<>()\[\]\x5C.,;:@" ]+)*@$/;
            var localPartfilter2 = /^"[^\r\n]+"@$/;
            var domainfilter = /^([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]|[a-zA-Z0-9])(\.([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]|[a-zA-Z0-9]))*$/;
            var sepPos = 0;
            var localPart;
            var domain;
            var localPartOk = false;
            var domainOk    = false;
            sepPos = s.lastIndexOf("@");
            localPart = s.substring(0,sepPos+1);
            domain    = s.substring(sepPos+1,s.length);

            if  (localPartfilter1.test(localPart))
                localPartOk = true;
            else if (localPartfilter2.test(localPart))
                localPartOk = true;
            else
                localPartOk = false;

            if (domainfilter.test(domain))
                domainOk = true;
            else
                domainOk = false;

            if ( (localPartOk==true && domainOk==true)||(s=="") )
                return true;
            else
                alert(" Введите правильный формат адреса электронной почты!" );
            theElement.focus();
            theElement.select();
            return false;
        }
    </script>

</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="emplForm" class="form-signin">

        <h2 class="form-signin-heading">Create empl</h2>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="lastname" name="lastname" placeholder="Lastname" type="text" autofocus="true" class="form-control" value="${semployeeWrapper.semployee.lastname}">
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="firstname" name="firstname" placeholder="Firstname" type="text" autofocus="true" class="form-control" value="${semployeeWrapper.semployee.firstname}">
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="middlename" name="middlename" placeholder="Middlename" type="text" autofocus="true" class="form-control" value="${semployeeWrapper.semployee.middlename}">
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="email" name="email" placeholder="Email" type="text" autofocus="true" class="form-control" value="${semployeeWrapper.semployee.email}">
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="emplTelephone" name="emplTelephone" placeholder="EmplTelephone" type="text" autofocus="true" class="form-control" value="${semployeeWrapper.semployee.emplTelephone}">
        </div>
        <div class="form-group">
            <select class="form-control" id="typeEmployeeId" name="typeEmployeeId" onchange="blockData()">
                <c:forEach var="row" items="${typeEmployeesMap}" varStatus="loop">
                    <option value="${row.key}">${row.value}</option>
                </c:forEach>

            </select>
        </div>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <input id="emplTelephoneWork" name="emplTelephoneWork" placeholder="EmplTelephoneWork" type="text" autofocus="true" class="form-control" value="${semployeeWrapper.semployee.emplTelephoneWork}">
        </div>
        <div class="form-group">
            <select class="form-control" id="orgId" name="orgId">
                <c:forEach var="row" items="${organizationMap}" varStatus="loop" >
                    <option value="${row.key}">${row.value}</option>
                </c:forEach>
            </select>
        </div>


        <%--<spring:bind path="lastname">--%>
        <%-- <div class="form-group ${status.error ? 'has-error' : ''}">
             <form:input type="text" path="lastname" class="form-control" placeholder="Lastname"
                         autofocus="true"  ></form:input>
             <form:errors path="lastname"></form:errors>
         </div>--%>
        <%--</spring:bind>--%>

        <%-- <spring:bind path="firstname">
              <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" path="firstname" class="form-control" placeholder="Firstname"
                              autofocus="true" value="${semployeeWrapper.semployee.firstname}"></form:input>
                  <form:errors path="firstname"></form:errors>
              </div>
          </spring:bind>--%>
        <%--<spring:bind path="middlename">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="middlename" class="form-control" placeholder="Middlename"
                            autofocus="true" value="${semployeeWrapper.semployee.middlename}"></form:input>
                <form:errors path="middlename"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="Email"
                            autofocus="true" ></form:input>
                <form:errors path="email"></form:errors>
            </div>
        </spring:bind>


        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="text" path="emplTelephone" class="form-control" placeholder="EmplTelephone"
                        autofocus="true"></form:input>
            <form:errors path="emplTelephone"></form:errors>
        </div>



        <div class="form-group">
            <form:select class="form-control" path="typeEmployeeId" onchange="blockData()">
                <form:options items="${typeEmployeesMap}"/>
            </form:select>
        </div>
        <spring:bind path="emplTelephoneWork">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="emplTelephoneWork" style="display" id="workPhone" class="form-control" placeholder="EmplTelephoneWork"
                            autofocus="true"></form:input>
                <form:errors path="emplTelephoneWork"></form:errors>
            </div>
        </spring:bind>
        <div class="form-group">
            <form:select class="form-control" id="org" style="display" path="orgId">
                <form:option value=""/>
                <form:options items="${organizationMap}"/>
            </form:select>
        </div>--%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>

    </form:form>
</div>
<!-- /container -->
<%--<script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
</body>
</html>
