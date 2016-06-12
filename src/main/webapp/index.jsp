<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Information</title>
</head>
<body>
<a href="<c:url value="/companies"/>"><h1>Companies list</h1></a>
<br/>
<br/>
<br/>
<br/>
Реализация тестового задания:<br/>
Задание реализовано в виде таблицы - выборки из базы данных по полю parrentId - ID родительской компании.<br/>
Для реализации связей между компаниями я добавил выше упомянутое поле parrentId. Т.е. дочерняя компания<br/>
знает ID своей родительской компании. С другой стороны, родительская компания ничего не знает о своих дочерних компаниях,<br/>
так как в этом нет необходимости. Все, что ей нужно знать - это свой ID, и по нему можно сделать запрос в БД.<br/>
По умолчанию поле parrentId = 0, т.е. компания не имеет родительской компании, и на главной странице отображаются эти компании.<br/>
При выборе определенной компании из списка (нажатием на ее имя) формируется новая страница<br/>
с таблицей дочерних компаний выбранного предприятия, если таковые имеются.<br/>
При удалении компании, ее дочерние компании не удаляются, а полю parrentId дочерней комп. присваивается значение 0.<br/>
Данные компании можно редактировать, но поля ID и parrentId скрыты от пользователя.<br/>
<br/>
Дерево отображается в таком виде, как указано в тестовом задании, на любой глубине вложенных дочерних компаний, <br/>
подсчет общей суммы доходов главной и дочерних компаний также соответствует заданию.<br/>
<br/>
<br/>
Update: Обновил отображение дерева компаний, добавил возможность выбрать предприятие из дерева и сформировать новую страницу<br/>
с отображением дерева выбраной компании (как в списке).<br/>
Также добавил вожможность редактирования, удаления записей, добавление дочерних компаний. Теперь можно полноценно работать с<br/>
записями таблицы как из страницы со списком компаний, так и со страницы с деревом компаний.<br/>
Добавил кнопки перехода вверх по страктуре вложености компаний, и кнопку возврата на предидущую страницу из истории.<br/>
<br/>
Приложение реализовано с помощью технологий Spring MVC/Hibernate/Servlet. Используется база данных PostgreSQL, в каталоге ресурсов<br/>
хранятся файлы инициализации и популирования БД.<br/>
<br/>
<br/>
<br/>
<a href="https://github.com/melnyknikolay/company">GitHub</a>
</body>
</html>
