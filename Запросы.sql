1. Запрос с фильтрацией и сортировкой. Вывести количество детей у работников, чьи зарплаты выше 10000.
SELECT 
	e.last_name,
	e.children_count
FROM employee e
WHERE e.salary >= 10000
ORDER BY ASC

2. Запрос с GROUP BY. Вывести фамилии работников (employee) для каждого отдела (department), у которых зп выше среднего по отделу, 3. Запрос с HAVING, 4. Запрос с INNER JOIN
SELECT 
	d.department_name,
	ARRAY_AGG(e.last_name)
FROM department_employee de
	JOIN employee e ON e.employee_id = de.employee_id
	JOIN department d on d.department_id = de.department_id
	HAVING e.salary > AVG(e.salary)
GROUP BY de.department_id, d.department_name//todo: need this last?

5. Запрос с OUTER JOIN. Найти всех работников, которые ещё не принадлежат никакому отделу. (стажируются, например). и при этом имеют пол работника, который не имеет детей
SELECT
	*
FROM 
	employee e
	LEFT JOIN department_employee de on de.employee_id = e.employee_id
WHERE de.department_id = NULL //todo: or is NULL

6. Запрос с подзапросом

