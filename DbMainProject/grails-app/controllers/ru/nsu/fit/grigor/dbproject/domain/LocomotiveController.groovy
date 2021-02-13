package ru.nsu.fit.grigor.dbproject.domain

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LocomotiveController {

    LocomotiveService locomotiveService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond locomotiveService.list(params), model:[locomotiveCount: locomotiveService.count()]
    }

    def show(Long id) {
        respond locomotiveService.get(id)
    }

    def create() {
        respond new Locomotive(params)
    }

    def save(Locomotive locomotive) {
        if (locomotive == null) {
            notFound()
            return
        }

        try {
            locomotiveService.save(locomotive)
        } catch (ValidationException e) {
            respond locomotive.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'locomotive.label', default: 'Locomotive'), locomotive.id])
                redirect locomotive
            }
            '*' { respond locomotive, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond locomotiveService.get(id)
    }

    def update(Locomotive locomotive) {
        if (locomotive == null) {
            notFound()
            return
        }

        try {
            locomotiveService.save(locomotive)
        } catch (ValidationException e) {
            respond locomotive.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'locomotive.label', default: 'Locomotive'), locomotive.id])
                redirect locomotive
            }
            '*'{ respond locomotive, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        locomotiveService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'locomotive.label', default: 'Locomotive'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'locomotive.label', default: 'Locomotive'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
