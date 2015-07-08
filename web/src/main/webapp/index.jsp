<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
</head>
<script type="text/javascript" src="lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {

        $("#add-user-btn").click(function () {
            $("#user-table").append("<tr id='input-tr'>" +
            "<td><input type='text'/></td>" +
            "<td><input type='text'/></td>" +
            "<td><input type='text'/></td>" +
            "<td><input type='text'/></td>" +
            "</tr>");
            $("#add-user-btn").hide();
            $("#confirm-add-btn").show();
        });

        $("#confirm-add-btn").click(function (){
            var data = {
                "operation":"add",
                "username":$("#input-tr").find("input")[0].value,
                "gender":$("#input-tr").find("input")[1].value == "男"? 1 : 0,
                "email":$("#input-tr").find("input")[2].value,
                "age":$("#input-tr").find("input")[3].value
            }

            console.log(JSON.stringify(data));

            $.post("/web/user", data, function(resp){
                console.log(resp);
                var user = resp;
                $("#user-table").append("<tr><span style='display:none;'>" + user.id + "</span>" +
                "<td>" + user.username + "</td>" +
                "<td>" + user.gender + "</td>" +
                "<td>" + user.email + "</td>" +
                "<td>" + user.age + "</td>" +
                "</tr>");
            });


            $("#add-user-btn").show();
            $("#confirm-add-btn").hide();
            $("#input-tr").remove();
        });

        $.get("/web/user", function (data, status) {
            var users = data;
            for (var i = 0; i < users.length - 1; i++) {
                $("#user-table").append("<tr><span style='display:none;'>" + users[i].id + "</span>" +
                "<td>" + users[i].username + "</td>" +
                "<td>" + users[i].gender + "</td>" +
                "<td>" + users[i].email + "</td>" +
                "<td>" + users[i].age + "</td>" +
                "</tr>");
            }

        });
    });


</script>
<body>
<div>
    <div>
        <table id="user-table">
            <tr>
                <td>姓名</td>
                <td>性别</td>
                <td>邮箱</td>
                <td>年龄</td>
            </tr>
            <%
                String[][] info = {
                        {"赵明", "男", "zhaoming@tw.com", "20"},
                        {"小黄", "女", "xiaohuang@tw.com", "18"},
                        {"王丽", "女", "wangli@tw.com", "20"},
                };
                for (int i = 0; i < 3; i++) {
                    out.println("<tr>");
                    for (int j = 0; j < 4; j++) {
                        out.println("<td>");
                        out.println(info[i][j].toString());
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
            %>
        </table>
    </div>
    <div id="op-bar">
        <button id="add-user-btn" type="button">添加用户</button>
        <button id="confirm-add-btn" type="button" style='display: none;'>确认添加</button>
    </div>
</div>
</body>
</html>