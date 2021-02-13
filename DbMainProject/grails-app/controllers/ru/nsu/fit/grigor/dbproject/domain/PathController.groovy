package ru.nsu.fit.grigor.dbproject.domain

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PathController {

    PathService pathService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pathService.list(params), model:[pathCount: pathService.count()]
    }

    def show(Long id) {
        respond pathService.get(id)
    }

    def create() {
        respond new Path(params)
    }

    def save(Path path) {
        if (path == null) {
            notFound()
            return
        }

        try {
            pathService.save(path)
        } catch (ValidationException e) {
            respond path.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'path.label', default: 'Path'), path.id])
                redirect path
            }
            '*' { respond path, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond pathService.get(id)
    }

    def update(Path path) {
        if (path == null) {
            notFound()
            return
        }

        try {
            pathService.save(path)
        } catch (ValidationException e) {
            respond path.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'path.label', default: 'Path'), path.id])
                redirect path
            }
            '*'{ respond path, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        pathService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'path.label', default: 'Path'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'path.label', default: 'Path'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
