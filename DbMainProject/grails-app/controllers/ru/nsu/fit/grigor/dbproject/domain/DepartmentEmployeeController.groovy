package ru.nsu.fit.grigor.dbproject.domain

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class DepartmentEmployeeController {

    DepartmentEmployeeService departmentEmployeeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond departmentEmployeeService.list(params), model:[departmentEmployeeCount: departmentEmployeeService.count()]
    }

    def show(Long id) {
        respond departmentEmployeeService.get(id)
    }

    def create() {
        respond new DepartmentEmployee(params)
    }

    def save(DepartmentEmployee departmentEmployee) {
        if (departmentEmployee == null) {
            notFound()
            return
        }

        try {
            departmentEmployeeService.save(departmentEmployee)
        } catch (ValidationException e) {
            respond departmentEmployee.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'departmentEmployee.label', default: 'DepartmentEmployee'), departmentEmployee.id])
                redirect departmentEmployee
            }
            '*' { respond departmentEmployee, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond departmentEmployeeService.get(id)
    }

    def update(DepartmentEmployee departmentEmployee) {
        if (departmentEmployee == null) {
            notFound()
            return
        }

        try {
            departmentEmployeeService.save(departmentEmployee)
        } catch (ValidationException e) {
            respond departmentEmployee.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'departmentEmployee.label', default: 'DepartmentEmployee'), departmentEmployee.id])
                redirect departmentEmployee
            }
            '*'{ respond departmentEmployee, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        departmentEmployeeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'departmentEmployee.label', default: 'DepartmentEmployee'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'departmentEmployee.label', default: 'DepartmentEmployee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
