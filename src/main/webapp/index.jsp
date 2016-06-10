<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Company manager</title>
</head>
<body>
<h3>Company manager</h3>
<br/>
<a href="<c:url value="/companies/0"/>">Companies list</a>
<br/>
<br/>
<br/>
<br/>
Реализация тестового задания:<br/>
Задание реализовано в виде таблицы - выборки из базы данных по полю parrentId - ID родительской компании.<br/>
Для реализации связей между компаниями я добавил выше упомянутое поле parrentId. Т.е. дочерняя компания<br/>
знает ID своей родительской компании. С другой стороны, родительская компания ничего не знает о своих дочерних компаниях,<br/>
так как в этом нет необходимость. Все, что ей нужно знать - это свой ID, и по нему можно сделать запрос в БД.<br/>
По умолчанию поле parrentId = 0, т.е. компания не имеет родительской компании, и на главной стрвнице отображаются ети компании.<br/>
При выборе опреленной компании из списка (нажатием на ее имя) формируется новая страница<br/>
с таблицей дочерних компаний выбраного предприятия, если таковые имеются.<br/>
Под таблицей имеется форма для добавления новой компании, в которой есть скрытое поле parrentId, <br/>
и в это поле заносится параметр parrentId текущей страницы.<br/>
При удалении компании, ее дочерние компании не удаляются, а полю parrentId дочерней комп. присваивается значение 0.<br/>
Данные компании можно редактировать, но поля ID и parrentId скрыты от пользователя.<br/>
<text-decoration:line-through>Отображение дерева компаний я захардкодил до второго уровня вложености,
а подсчет доходов - только по первому уровню (родитель + дочерние комп. )
реализовать по нормальному не хватило времени, т к. очень хотел успеть до выходных, а днем - на работе</text-decoration:line-through><br/>
Дерево отображается так, как указано в тестовом задании, на любой глубине вложенных дочерних компаний, <br/>
подсчет общей суммы доходов главной и дочерних компаний также правильно работает.<br/>
<br/>
Приложение реализовано с помощью технологий Sprig MVC/Hibernate/Servlet. Используется база данных PostgreSQL, в каталоге ресурсов<br/>
хранятся файлы инициализации и популирования БД.<br/>
<br/>
<br/>
<br/>
<a href="https://github.com/melnyknikolay/company">GitHub</a>
</body>
</html>
