package ru.nsu.fit.grigor.dbproject.domain

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class HealthCheckController {

    HealthCheckService healthCheckService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond healthCheckService.list(params), model:[healthCheckCount: healthCheckService.count()]
    }

    def show(Long id) {
        respond healthCheckService.get(id)
    }

    def create() {
        respond new HealthCheck(params)
    }

    def save(HealthCheck healthCheck) {
        if (healthCheck == null) {
            notFound()
            return
        }

        try {
            healthCheckService.save(healthCheck)
        } catch (ValidationException e) {
            respond healthCheck.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'healthCheck.label', default: 'HealthCheck'), healthCheck.id])
                redirect healthCheck
            }
            '*' { respond healthCheck, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond healthCheckService.get(id)
    }

    def update(HealthCheck healthCheck) {
        if (healthCheck == null) {
            notFound()
            return
        }

        try {
            healthCheckService.save(healthCheck)
        } catch (ValidationException e) {
            respond healthCheck.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'healthCheck.label', default: 'HealthCheck'), healthCheck.id])
                redirect healthCheck
            }
            '*'{ respond healthCheck, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        healthCheckService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'healthCheck.label', default: 'HealthCheck'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'healthCheck.label', default: 'HealthCheck'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
