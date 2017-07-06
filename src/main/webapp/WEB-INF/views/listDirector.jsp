<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Denis">

    <title>Employee</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">


    <script language="JavaScript 1.1" type="text/javascript">
        function select_value_n(P_VALUE, P_SCRIPT)
        {
            var type = document.getElementsByName("typeEmpl")[0].value;
            var P_COLUMN ;
            if (type ==1 ) P_COLUMN = 'directorId$directorName';
            else P_COLUMN = 'contactId$contactName';
            var slicedVALUE=P_VALUE.split("$");
            var slicedCOLUMN=P_COLUMN.split("$");
            var slicedFORM;
            var doc=opener.document;
            for(var i=0;i<slicedCOLUMN.length;i++){
                doc.forms[0].elements[slicedCOLUMN[i]].value=slicedVALUE[i];
            }
            if(P_SCRIPT != "" && P_SCRIPT != undefined)
                eval( P_SCRIPT );
            window.close();
        }
    </script>
</head>
<body>
<br>
<br>
<div class="container">
    <input type="hidden" name="typeEmpl" value=${typeEmpl}>
    <table class="table table-bordered">
        <thead class="thead-inverse">
        <tr>
            <th>#</th>
            <th>Fio</th>

        </tr>
        </thead>
        <c:forEach var="row" items="${semployeeListWork}" varStatus="loop">
            <tr>
                <td>${loop.index+1}</td>
                <td id=${row.id}><a href="javascript:select_value_n('${row.id}$${row.lastname} ${row.firstname} ${row.middlename}')">${row.lastname} ${row.firstname} ${row.middlename} </a></td>
            </tr>
        </c:forEach>
    </table>




</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>