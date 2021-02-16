0. Идеальные формы: 1)таблица team, из неё можем в department, добавить, потом обратно; 2) таблица health_check, из неё можно в employee, создать, потом обратно; 3) таблица department_employee, из неё можно и в employee, и в department, добавить, потом обратно; 4) таблица path, из неё можно в station, добавить,       перейти обратно;

1. Запрос с фильтрацией и сортировкой. Вывести количество детей у работников, чьи зарплаты выше 10000.
SELECT 
	e.name,
	e.children_count
FROM employee e
WHERE e.salary >= 10000
ORDER BY ASC

2. Запрос с GROUP BY.
SELECT
	d.department_name,
	ARRAY_AGG(e.name)
FROM department_employee de
	JOIN employee e ON e.employee_id = de.employee_id
	JOIN department d on d.department_id = de.department_id
GROUP BY de.department_id, d.department_name//todo: need this last?

3. Запрос с HAVING, 
Вывести фамилии работников (employee) для каждого отдела (department), у которых зп выше среднего по отделу
SELECT
	d.department_name,
	ARRAY_AGG(e.last_name)
FROM department_employee de
	JOIN employee e ON e.employee_id = de.employee_id
	JOIN department d on d.department_id = de.department_id
	HAVING e.salary > AVG(e.salary)
GROUP BY de.department_id, d.department_name//todo: need this last?

4. Запрос с INNER JOIN
вывести фамилии работников(employee), которые принадлежат хоть какому-то отделу (department)
SELECT 
	e.last_name
FROM 
	department_employee de
JOIN employee e on 
	e.employee_id = de.employee_id

5. Запрос с OUTER JOIN. 
Вывести фамилии всех работников(employee), которые ещё не определены ни в какой отдел (department)
SELECT 
	e.last_name
FROM 
	employee e
LEFT JOIN department_employee de on 
	de.employee_id = e.employee_id
WHERE de.department_id = NULL // or IS NULL

6. Запрос с подзапросом.
Найти всех работников, которые ещё не принадлежат никакому отделу и при этом их зарплата выше зарплаты данного работника (параметризованный запрос). Вывести их фамилии (given_employee_id)
SELECT
	*
FROM 
	employee e
	LEFT JOIN department_employee de on de.employee_id = e.id
WHERE 
	de.department_id is NULL
AND 
	e.salary > (SELECT 
					e.salary
				FROM 
					employee e
				WHERE e.id = {parameter_id}
				)  
				
SELECT
	*
FROM 
	Employee e
	LEFT JOIN e.Department_Employee de
WHERE 
	de.department_id is NULL
AND 
	e.salary > (SELECT 
					e.salary
				FROM 
					employee e
				WHERE e.id = {parameter_id}
				)  


			
Доп. баллы:
1. Индекс для запроса
CREATE INDEX gender_index ON gender;
// насчет индексов в grails 
// https://stackoverflow.com/questions/17478879/when-and-how-do-i-have-to-create-an-index-in-grails

2. Триггер (допилить)
Пример создания таблицы со свойством autoIncrement у столбца ID:
CREATE TABLE STATION (
id INT NOT NULL,
name VARCHAR(30) NOT NULL,
PRIMARY KEY (id)
);
CREATE SEQUENCE STATION_ai
START WITH 1
INCREMENT BY 1
NOMAXVALUE;
CREATE OR REPLACE TRIGGER tr_ai before INSERT ON station FOR each row
BEGIN
SELECT STATION_ai.NEXTVAL
INTO :new.id
FROM dual;
END;



















