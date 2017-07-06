<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Staff</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function search() {
            var value = document.getElementsByName("serch")[0].value;
            if (value != "") {
                window.location = "/result?stypeId=" + value;
            }
            else window.location = "/index";
        }
        function newEmployee() {
            window.location = "/emplForm";
        }
        function newOrganization() {
            window.location = "/organizationForm";
        }
        function sorted(valueSorted, order) {
            var value = document.getElementsByName("serch")[0].value;
            window.location = "/sort?stypeId=" + value + "&valueSorted=" + valueSorted + "&ascDesc=" + order;
            /*if (value != "") {

             }
             else window.location = "/index";*/

        }
    </script>
</head>
<body>

<br>
<br>
<div class="container">
    <button type="button" onclick="search()">Inquiry</button>
    <button type="button" onclick="newEmployee()">New Empl</button>
    <button type="button" onclick="newOrganization()">New Organization</button>
    <br><br>
    <%--<form:form modelAttribute="myform" action="result" method="get" >--%>
    <%--<select class="form-control" id="type" name="orgId">
        <option value=""></option>
        <c:forEach var="row" items="${organizationMap}" varStatus="loop">
            <option value="${row.key}">${row.value}</option>
        </c:forEach>
    </select>--%>
    <form:select path="typeEmployeesMap" name="serch">
        <form:option value=""/>
        <form:options items="${typeEmployeesMap}"/>
    </form:select>
    <%--</form:form>--%>
    <br>
    <br>
</div>


<div class="container">
    <table class="table table-bordered">
        <thead class="thead-inverse">
        <tr>
            <th>#</th>
            <th>Fio<br>
                <c:if test="${displayNoneAscLastName != null}">
                    <img src="${contextPath}/resources/image/expup.gif" onclick="sorted('lastname', 'asc')">
                    <img src="${contextPath}/resources/image/expaup.gif" style='display:none'
                         onclick="sorted('lastname', 'asc')">
                </c:if>
                <c:if test="${displayNoneAscLastName == null}">
                    <img src="${contextPath}/resources/image/expup.gif" style='display:none'
                         onclick="sorted('lastname', 'asc')">
                    <img src="${contextPath}/resources/image/expaup.gif" onclick="sorted('lastname', 'asc')">
                </c:if>

                <c:if test="${displayNoneDescLastName != null}">
                    <img src="${contextPath}/resources/image/expdown.gif" onclick="sorted('lastname', 'desc')">
                    <img src="${contextPath}/resources/image/expadown.gif" style='display:none'
                         onclick="sorted('lastname', 'desc')">
                </c:if>
                <c:if test="${displayNoneDescLastName == null}">
                    <img src="${contextPath}/resources/image/expdown.gif" style='display:none'
                         onclick="sorted('lastname', 'desc')">
                    <img src="${contextPath}/resources/image/expadown.gif" onclick="sorted('lastname', 'desc')">
                </c:if>
            </th>
            <th>Type Employee</th>
            <th>Telephone Employee</th>
            <th>Date Create Employee<br>
                <c:if test="${displayNoneAscDate != null}">
                    <img src="${contextPath}/resources/image/expup.gif" onclick="sorted('emplCreateDate', 'asc')">
                    <img src="${contextPath}/resources/image/expaup.gif" style='display:none'
                         onclick="sorted('emplCreateDate', 'asc')">
                </c:if>
                <c:if test="${displayNoneAscDate == null}">
                    <img src="${contextPath}/resources/image/expup.gif" style='display:none'
                         onclick="sorted('emplCreateDate', 'asc')">
                    <img src="${contextPath}/resources/image/expaup.gif" onclick="sorted('emplCreateDate', 'asc')">
                </c:if>

                <c:if test="${displayNoneDescDate != null}">
                    <img src="${contextPath}/resources/image/expdown.gif" onclick="sorted('emplCreateDate', 'desc')">
                    <img src="${contextPath}/resources/image/expadown.gif" style='display:none'
                         onclick="sorted('emplCreateDate', 'desc')">
                </c:if>
                <c:if test="${displayNoneDescDate == null}">
                    <img src="${contextPath}/resources/image/expdown.gif" style='display:none'
                         onclick="sorted('emplCreateDate', 'desc')">
                    <img src="${contextPath}/resources/image/expadown.gif" onclick="sorted('emplCreateDate', 'desc')">
                </c:if>

            </th>
            <th>Organization</th>

        </tr>
        </thead>

        <c:forEach var="row" items="${semployeeListAll}" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
                <td>
                    <a href="${contextPath}/cardEmployee?emplId=${row.id}">${row.lastname} ${row.firstname} ${row.middlename}</a>
                </td>
                <td>${row.typeEmployee}</td>
                <td>${row.emplTelephone}</td>
                <td>${row.emplCreateDate}</td>
                <td><a href="${contextPath}/cardOrganization?orgId=${row.organization3.id}">${row.organization3}</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
<script>
    var select = document.getElementsByName("serch")[0].value = "${options}";
</script>
</body>
</html>