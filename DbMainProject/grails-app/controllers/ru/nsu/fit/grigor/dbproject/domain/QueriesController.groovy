package ru.nsu.fit.grigor.dbproject.domain

import groovy.sql.Sql

import javax.sql.DataSource


class QueriesController {


    def index() {
    }

    /*
       Запрос с фильтрацией и сортировкой.
       Вывести количество детей у работников, чьи зарплаты выше {число}.
    */
    def filteringAndSorting = {
        params.salaryFrom = params.salaryFrom ?: 10000
        Long salaryFrom = params.salaryFrom as Long
        def results = Employee.listOrderByName(order: 'asc').findAll {
            it.salary >= salaryFrom
        }

        render(view: 'filteringAndSorting',
                model: [results: results, salaryFrom: salaryFrom, resultCount: results.size()])
    }

    /*
        вывести всех работников (employee) для каждого отдела (department)
    */
    def groupBy = {
        /*
SELECT
	d.department_name,
	ARRAY_AGG(e.name)
FROM department_employee de
	JOIN employee e ON e.employee_id = de.employee_id
	JOIN department d on d.department_id = de.department_id
GROUP BY de.department_id, d.department_name
        * */
        def criteria = DepartmentEmployee.createCriteria()
        /*def results = criteria.list{
            groupByParallel { it.department }
        }
*/

        render(view: 'groupBy',
                model: [results: results, resultCount: results.size()])
    }

    /*
        Вывести фамилии работников (employee) для каждого отдела (department),
        у которых зп выше среднего по отделу
    */
    def having = {
        params.salaryFrom = params.salaryFrom ?: 10000
        Long salaryFrom = params.salaryFrom as Long
        def results = Employee.listOrderByName(order: 'asc').findAll {
            it.salary >= salaryFrom
        }

        render(view: 'having',
                model: [results: results, salaryFrom: salaryFrom, resultCount: results.size()])

    }

    /*
        вывести фамилии работников(employee),
        которые принадлежат хоть какому-то отделу (department)
    */
    def innerJoin = {
        params.salaryFrom = params.salaryFrom ?: 10000
        Long salaryFrom = params.salaryFrom as Long
        def results = Employee.listOrderByName(order: 'asc').findAll {
            it.salary >= salaryFrom
        }

        render(view: 'innerJoin',
                model: [results: results, salaryFrom: salaryFrom, resultCount: results.size()])

    }

    /*
        Вывести фамилии всех работников(employee),
        которые ещё не определены ни в какой отдел (department)
    */
    def outerJoin = {
        params.salaryFrom = params.salaryFrom ?: 10000
        Long salaryFrom = params.salaryFrom as Long
        def results = Employee.listOrderByName(order: 'asc').findAll {
            it.salary >= salaryFrom
        }

        render(view: 'outerJoin',
                model: [results: results, salaryFrom: salaryFrom, resultCount: results.size()])

    }

    /*
        Найти всех работников, которые ещё не принадлежат никакому отделу
        и при этом их зарплата выше зарплаты данного работника (параметризованный запрос).
        Вывести их фамилии (given_employee_id)
    */

    def dataSource

    def subQuery = {
        def employees = Employee.listOrderById(order: 'asc')
        def employeeId = params.employee
        if (employeeId == null) {
            render(view: 'subQuery',
                    model: [results    : null,
                            resultCount: null,
                            employees  : employees
                    ])
            return
        }
        def queryString = "SELECT e.id FROM Employee e LEFT JOIN department_employee de on de.employee_id = e.id " +
                "WHERE de.department_id is NULL AND " +
                "e.salary > (SELECT e.salary FROM employee e WHERE e.id = ${employeeId})"
        def sql = new Sql(dataSource as DataSource)
        List<Employee> results = new ArrayList<Employee>()
        def rows = sql.rows(queryString).each {
            results.add(Employee.findById(it.id))
        }

        render(view: 'subQuery',
                model: [results    : results,
                        resultCount: results.size(),
                        employees  : employees
                ])

    }
}
