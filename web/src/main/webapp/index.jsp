<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
</head>
<script type="text/javascript" src="lib/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

    function update_operation() {
        $(".edit_user_btn").unbind("click");
        $(".delete_user_btn").unbind("click");

        //编辑
        $(".edit_user_btn").click(function (e) {
            //获取id
            var user_row = $(this).closest("tr");
            var user_id = user_row.attr("id").substr("user_row_".length);
            //获取信息
            var user_info = {
                "id": user_id,
                "username": user_row.find(".col_username").text(),
                "gender": user_row.find(".col_gender").text(),
                "email": user_row.find(".col_email").text(),
                "age": user_row.find(".col_age").text()
            };
            console.log(JSON.stringify(user_info));
            //隐藏原始信息
            user_row.find("td:lt(4)").text("");
            user_row.find(".col_operation").children().hide();
            //添加输入
            user_row.find(".col_username").append("<input type='text' value='" + user_info.username + "'/>");
            user_row.find(".col_gender").append("<input type='text' value='" + user_info.gender + "'/>");
            user_row.find(".col_email").append("<input type='text' value='" + user_info.email + "'/>");
            user_row.find(".col_age").append("<input type='text' value='" + user_info.age + "'/>");
            //添加处理
            var done_btn = $("<button class='edit_user_done_btn' type='button'>确定</button>");
            done_btn.click(function () {
                var data = {
                    "operation": "edit",
                    "id": user_info.id,
                    "username": user_row.find("input")[0].value,
                    "gender": user_row.find("input")[1].value,
                    "email": user_row.find("input")[2].value,
                    "age": user_row.find("input")[3].value
                };
                //发送请求
                $.post("/web/user", data, function (resp) {
                    console.log(resp);
                    var user = resp;
                    user_row.find("td:lt(4)").empty();
                    user_row.find(".col_operation").children().show();
                    done_btn.remove();
                    user_row.find(".col_username").text(user.username);
                    user_row.find(".col_gender").text(user.gender);
                    user_row.find(".col_email").text(user.email);
                    user_row.find(".col_age").text(user.age);
                });
            });
            user_row.find(".col_operation").append(done_btn);
        });
        //删除
        $(".delete_user_btn").click(function (e) {
            //获取id
            var user_row = $(this).closest("tr");
            var user_id = user_row.attr("id").substr("user_row_".length);
            //获取信息
            var user_info = {
                "id": user_id,
                "username": user_row.find(".col_username").text(),
                "gender": user_row.find(".col_gender").text(),
                "email": user_row.find(".col_email").text(),
                "age": user_row.find(".col_age").text()
            };
            var data = {
                "operation": "delete",
                "id": user_info.id,
                "username": user_info.username,
                "gender": user_info.gender,
                "email": user_info.email,
                "age": user_info.age
            };
            console.log(JSON.stringify(data));
            //发送请求
            $.post("/web/user", data, function (resp) {
                console.log(resp);
                user_row.empty();
                user_row.remove();
            });
        });

    }

    $(document).ready(function () {


        $("#add-user-btn").click(function () {
            $("#user-table").append("<tr id='input-tr-add'>" +
            "<td class='col_username'><input type='text'/></td>" +
            "<td class='col_gender'><input type='text'/></td>" +
            "<td class='col_email'><input type='text'/></td>" +
            "<td class='col_age'><input type='text'/></td>" +
            "<td class='col_operation'></td>" +
            "</tr>");
            update_operation();
            $("#add-user-btn").hide();
            $("#confirm-add-btn").show();
        });

        $("#confirm-add-btn").click(function () {

            var data = {
                "operation": "add",
                "username": $("#input-tr-add").find("input")[0].value,
                "gender": $("#input-tr-add").find("input")[1].value,
                "email": $("#input-tr-add").find("input")[2].value,
                "age": $("#input-tr-add").find("input")[3].value
            };

            console.log(JSON.stringify(data));
            $.post("/web/user", data, function (resp) {
                console.log(resp);
                var user = resp;
                $("#user-table").append("<tr id='user_row_" + user.id + "'>" +
                "<td class='col_username'>" + user.username + "</td>" +
                "<td class='col_gender'>" + user.gender + "</td>" +
                "<td class='col_email'>" + user.email + "</td>" +
                "<td class='col_age'>" + user.age + "</td>" +
                "<td class='col_operation'><button class='edit_user_btn' type='button'>编辑</button><button class='delete_user_btn' type='button'>删除</button></td>" +
                "</tr>");
                update_operation();
            });


            $("#add-user-btn").show();
            $("#confirm-add-btn").hide();
            $("#input-tr-add").remove();
        });

        $.get("/web/user", function (data, status) {
            var users = data;
            for (var i = 0; i < users.length - 1; i++) {
                $("#user-table").append("<tr id='user_row_" + users[i].id + "'>" +
                "<td class='col_username'>" + users[i].username + "</td>" +
                "<td class='col_gender'>" + users[i].gender + "</td>" +
                "<td class='col_email'>" + users[i].email + "</td>" +
                "<td class='col_age'>" + users[i].age + "</td>" +
                "<td class='col_operation'><button class='edit_user_btn' type='button'>编辑</button><button class='delete_user_btn' type='button'>删除</button></td>" +
                "</tr>");
            }
            update_operation();
        });
    });


</script>
<body>
<div>
    <div>
        <table id="user-table">
            <tr>
                <td class='col_username'>姓名</td>
                <td class='co_gender'>性别</td>
                <td class='col_email'>邮箱</td>
                <td class='col_age'>年龄</td>
                <td class='col_operation'>操作</td>
            </tr>
        </table>
    </div>
    <div id="op-bar">
        <button id="add-user-btn" type="button">添加用户</button>
        <button id="confirm-add-btn" type="button" style='display: none;'>确认添加</button>
    </div>
</div>
</body>
</html>