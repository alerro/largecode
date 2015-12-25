<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="http://code.jquery.com/jquery-latest.js">
</script>
<script>

    $(document).ready(function() {
        processJsonResponse = function(data){
            if(data.status == 'Ok') alert('Операция успешно завершена.');
            else alert('Код ошибки: ' + data.status + ', Описание: ' + data.errorMessage);
        };
        processData = function(data){
            alert(data);
        };
        $('a.jsonrequest').click(function(event) {
            thediv = $(this).closest("div");
            $.ajax({
                type: "POST",
                url: thediv.attr("class"),
                dataType:'json',
                data: thediv.children( "input" ).val(),
                contentType: "application/json;charset=utf-8",
                success: processJsonResponse
            });
        });

        $('a.simplerequest').click(function(event) {
            thediv = $(this).closest("div");
            $.ajax({
                type: "POST",
                url: thediv.attr("class"),
                dataType:'json',
                contentType: "application/json;charset=utf-8",
                success: processData
            });
        });

        $('a.login').click(function(event) {
            thediv = $(this).closest("div");
            $.ajax({
                type: "POST",
                url: thediv.attr("class"),
                data: "j_username="+thediv.children( "input[name='email']" ).val()+
                      "&j_password="+thediv.children( "input[name='password']" ).val(),
                success: processData
            });
        });
        $('a.logout').click(function(event) {
            thediv = $(this).closest("div");
            $.ajax({
                type: "GET",
                url: thediv.attr("class"),
                success: processData
            });
        });
        $('a.vote').click(function(event) {
            thediv = $(this).closest("div");
            $.ajax({
                type: "GET",
                url: "/api/votetohavelunch/"+thediv.children( "input" ).val(),
                success: processJsonResponse
            });
        });


    });
</script>

<div class="/j_spring_security_check" style="border: #a9a9a9">
    <label style="margin-right: 25px;">email:</label>
    <input type="text" style="width: 600px;" name="email" value="admin@admin.com"/>
    <br/>
    <label>password:</label>
    <input type="password" style="width: 600px;" name="password" value="12345678"/>
    <a class="login" href="javascript:void(0)" >Залогиниться</a>
    <br/>
</div>
<br/>

<div class="/j_spring_security_logout" style="border: #a9a9a9">
    <a class="logout" href="javascript:void(0)" >Выйти</a>
    <br/>
</div>

<br/>


<div class="/api/admin/restaurant/add">
    <input type="text" style="width: 600px;"
     value='{"name": "Пикассо","address": "Казань Чистопольская 20"}'/>
    <a class="jsonrequest" href="javascript:void(0)" >Добавить ресторан</a>
</div>
<br/>
<div class="/api/admin/restaurant/dish/add">
    <input type="text" style="width: 600px;"
      value='{"restaurantId": 3, "dishes" : [{"name":"Курица"},{"name":"Мясо"},{"name":"Рыба"}] }'/>
    <a class="jsonrequest"  href="javascript:void(0)">Добавить блюда к ресторану</a>
</div>
<br/>
<div class="/api/admin/restaurant/lunchmenu/update">
    <input type="text"  style="width: 600px;"
           value='{"restaurantId": 3, "dateString":"23.12.2015", "menuItems" :[{"dishId":1,"price":100},{"dishId":2,"price":200},{"dishId":3,"price":300}] }'/>
    <a class="jsonrequest"  href="javascript:void(0)">Обновить меню</a>
</div>
<br/>
<div class="/api/restaurants">
    <input type="text"  style="width: 600px;"/>
    <a class="simplerequest"  href="javascript:void(0)">Список всех ресторанов</a>
</div>
<br/>

<div>
    <input type="text"  style="width: 600px;"/>
    <a class="vote"  href="javascript:void(0)">Проголосовать за ресторан</a>
</div>
