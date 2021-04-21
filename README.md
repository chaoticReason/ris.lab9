<h1>Лабораторная работа №8</h1>

<h2>«Технологии Spring Boot, JPA»</h2>

Программа представляет приложение, реализованное в основном с помощью Spring Boot, MySQL, Jackson и JUnit 5. Jackson отвечает за сереализацию и десереализацию данных в разных форматах. 
Для валидации по xsd-схеме используется SAX-парсер.

<h2>Задание</h2>
1.	Выбрать поля базы данных для ввода и хранения информации о клиентах для выполнения лабораторной работы согласно своему номеру варианта. - <b>done</b><br/>
2.	Реализовать XML-файл с данными, его XSD-схему и API для добавления, чтения, обновления и удаления (CRUD) клиентов в СУБД из XML. - <b>done</b><br/>
3.	Разработать схему данных в которой для элементов типа Список предусмотрены отдельные справочники. - <b>done</b><br/>
4.	Реализовать схему данных в выбранной СУБД используя генератор классов xjc (JAXB) и hibernate. - <b>1/2 done</b><br/>
5.	На хорошую оценку дополнительно реализовать обратное преобразование (чтение из СУБД и маршаллинг в XML). - <b>done</b><br/>
6.	На отличную оценку предусмотреть аналогичное преобразование в JSON и обратно (рекомендуется gson). - <b>done</b><br/>
7.	На защите продемонстрировать операции преобразований из консоли, либо (рекомендуется) показать соответствующее покрытие тестами JUnit. - <b>тесты done</b><br/>
<br/>Сущность вида:<br/><br/>
<table>
	<tbody>
	<tr>
		<td>
			<p>Наименование поля
			</p>
		</td>
		<td>
			<p>Тип поля
			</p>
		</td>
		<td>
			<p>Обязательное (да, нет)
			</p>
		</td>
		<td>
			<p><strong>Вариант 7 </strong>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Фамилия
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Имя
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Отчество
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Дата рождения
			</p>
		</td>
		<td>
			<p>Дата
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Серия паспорта
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>№ паспорта
			</p>
		</td>
		<td>
			<p>Текстовый (с маской)
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Город проживания
			</p>
		</td>
		<td>
			<p><strong>Список </strong>(от 5 городов)
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Адрес факт.проживания
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Телефон моб
			</p>
		</td>
		<td>
			<p>Текстовый (с маской)
			</p>
		</td>
		<td>
			<p>Нет
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>E-mail
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Нет
			</p>
		</td>
		<td>
			<p><strong>Нечетный </strong>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Трудостроен
			</p>
		</td>
		<td>
			<p>Boolean
			</p>
		</td>
		<td>
			<p>Нет
			</p>
		</td>
		<td>
			<p><strong>Нечетный </strong>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Должность
			</p>
		</td>
		<td>
			<p>Текстовый
			</p>
		</td>
		<td>
			<p>Нет
			</p>
		</td>
		<td>
			<p><strong>Нечетный </strong>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Город прописки
			</p>
		</td>
		<td>
			<p><strong>Список </strong>(от 5 городов)
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p><strong>1,4,7... (N%3==1) </strong>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<p>Гражданство
			</p>
		</td>
		<td>
			<p><strong>Список </strong>
			</p>
		</td>
		<td>
			<p>Да
			</p>
		</td>
		<td>
			<p>Любой
			</p>
		</td>
	</tr>
	</tbody>
	</table>
