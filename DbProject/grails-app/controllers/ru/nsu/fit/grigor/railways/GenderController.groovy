package ru.nsu.fit.grigor.railways

import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GenderController extends RestfulController {

    static responseFormats = ['html', 'json']

    GenderService genderService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    GenderController() {
        super(Gender)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond genderService.list(params), model: [genderCount: genderService.count()]
    }

    def show(Long id) {
        respond genderService.get(id)
    }

    def create() {
        respond new Gender(params)
    }

    def save(Gender gender) {
        if (gender == null) {
            notFound()
            return
        }

        try {
            genderService.save(gender)
        } catch (ValidationException e) {
            respond gender.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gender.label', default: 'Gender'), gender.id])
                redirect gender
            }
            '*' { respond gender, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond genderService.get(id)
    }

    def update(Gender gender) {
        if (gender == null) {
            notFound()
            return
        }

        try {
            genderService.save(gender)
        } catch (ValidationException e) {
            respond gender.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'gender.label', default: 'Gender'), gender.id])
                redirect gender
            }
            '*' { respond gender, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        genderService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'gender.label', default: 'Gender'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gender.label', default: 'Gender'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
