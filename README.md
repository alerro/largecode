# largecode
Описание.
Пишу на русском, на английском я еще бы полдня потратил на написание данного труда.
Прошу прощения, что особо не писал комментарии в коде, работы много сейчас, а времени мало, да и код довольно простой.
Тесты не создавал.


Чтобы проверить работу приложения нужно:

1. создать пустую базу данных MySql с именем largetestdb.
2. задеплоить приложение, например на Tomcat, я его использовал для отладки.
3. зайти в директорию largetest-database и вызвать там команду: mvn liquibase:update.
   Это создаст в таблице lt_role 2 роли с именами ROLE_ADMIN и ROLE_USER. 
   В таблице lt_user создастся 4 тестовых пользователя:
   1. admin@admin.com - с ролью ROLE_ADMIN
   2. alex@user.com -   с ролью ROLE_USER
   3. vova@user.com -   с ролью ROLE_USER
   4. dima@user.com -   с ролью ROLE_USER

4. Для проверки можно конечно, использовать curl вызовы,но я сделал тестовую страницу для 
   удобства отладки и проверки приложения. Ее адрес - /test. 
   Т.е. примерно такой урл http://localhost:8080/test
  
 Там есть 7 операций:
  
 1. Залогиниться. Вводим email(один из 4-х созданных юзеров) и пароль - 12345678 для всех пользователей.
      Жмем Залогиниться - происходит Логин.
   
 2. Выйти - разлогиниваемся.

 3. Создать ресторан. Работает только для залогиненного админа, иначе ошибка ajax вызова типа Access Denied. 
    На вход подается JSON, его пример дан в поле ввода слева.

 4. Добавить блюда к ресторану. Работает только для залогиненного админа, иначе ошибка ajax вызова типа Access Denied. 
    На вход подается JSON, его пример дан в поле ввода слева.

 5. Обновить меню. Работает только для залогиненного админа, иначе ошибка ajax вызова типа Access Denied. 
    На вход подается JSON, его пример дан в поле ввода слева.

 6. Список всех ресторанов. Работает только для залогиненного пользователя или админа. Без логина ошибка ajax вызова. 
    На вход ничего не подается.

 7. Проголосовать за ресторан. Работает только для залогиненного пользователя или админа. Без логина ошибка ajax вызова. 
    На вход подается id ресторана.

API такое:

1.Log in.
урл -   /j_spring_security_check 
метод - POST   
Параметры:  
1)  j_username - email пользователя 
2)  j_password - пароль пользователя   

2.Log out.  
урл -   /j_spring_security_logout   
метод - GET 
Параметров нет.   


3.Создать ресторан   
урл -   /api/admin/restaurant/add   
метод - POST   
Параметры передаются в теле запроса как JSON.   
JSON имеет такой вид: {"name": "Пикассо","address": "Казань Чистопольская 20"}      
Задается имя ресторана и его адрес. Каждый запрос создает 1 ресторан.   
Возвращает:             
. если статус Ok - созданный ресторан в формате JSON, по сути возвращает такой же JSON только id добавляется         
. если статус Ошибка  - описание ошибки         
Может вызываться только админом. 

4.Добавить блюда к ресторану  
урл -   /api/admin/restaurant/dish/add 
метод - POST   
Параметры передаются в теле запроса как JSON.   
JSON имеет такой вид: {"restaurantId": 3, "dishes" : [{"name":"Курица"},{"name":"Мясо"},{"name":"Рыба"}] }  
Задается id ресторана и список блюд dishes,каждое блюдо имеет только поле name - название.         
Возвращает:          
. если статус Ok - все блюда данного ресторана в формате JSON           
. если статус Ошибка  - описание ошибки            
Может вызываться только админом. 

5.Обновить меню   
урл -   /api/admin/restaurant/lunchmenu/update  
метод - POST   
Параметры передаются в теле запроса как JSON.   
JSON имеет такой вид: {"restaurantId": 3, "date":"23.12.2015", "menuItems"  :[{"dishId":1,"price":100},{"dishId":2,"price":200},{"dishId":3,"price":300}] }  
Задается id ресторана, дата и список пунктов меню, каждый из которых имеет id блюда и цену. Таким образом каждый день ресторан может    менять набор блюд и цены на них.                 
Возвращает:                      
. если статус Ok - все пункты меню данного ресторана на указанную дату в формате JSON                       
. если статус Ошибка  - описание ошибки                     
Может вызываться только админом. 

6.Список всех ресторанов. Выводит список всех ресторанов и блюда, которые данный ресторан готовит в формате JSON. 
урл -   /api/restaurants   
метод - GET,POST  
Параметров нет.   
Работает для любого залогиненного пользователя. 

7.Отдать голос за ресторан.   
урл -   /api/restaurant/{restaurantId}/vote  
метод - POST  
Параметр id ресторана задается прямо в урле -restaurantId                                 
Возвращает:             
. если статус Ok - количество всех голосов,отданных этому ресторану на указанную дату в формате JSON                    
. если статус Ошибка  - описание ошибки               
Работает для любого залогиненного пользователя. 
