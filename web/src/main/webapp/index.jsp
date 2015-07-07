<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
</head>
<script type="text/javascript" src="lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    $.get("/web/user", function(data, status){
        var users = data;
        for(var i = 0; i < users.length - 1; i++){
            $("#user-table").append("<tr>" +
                    "<td>" + users[i].username + "</td>" +
                    "<td>" + users[i].gender + "</td>" +
                    "<td>" + users[i].email + "</td>" +
                    "<td>" + users[i].age + "</td>" +
            "</tr>");
        }

    });

</script>
<body>
<div>
    <table id="user-table">
        <tr>
            <td>姓名</td>
            <td>性别</td>
            <td>邮箱</td>
            <td>年龄</td>
        </tr>
        <%
            String [][] info= {
                    {"赵明","男","zhaoming@tw.com", "20"},
                    {"小黄","女","xiaohuang@tw.com", "18"},
                    {"王丽","女","wangli@tw.com", "20"},
            };
            for (int i=0; i<3; i++){
                out.println("<tr>");
                for (int j=0; j<4; j++){
                    out.println("<td>");
                    out.println(info[i][j].toString());
                    out.println("</td>");
                }
                out.println("</tr>");
            }
        %>
    </table>
</div>
</body>
</html>