package ru.nsu.fit.grigor.dbproject.domain

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StationController {

    StationService stationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond stationService.list(params), model:[stationCount: stationService.count()]
    }

    def show(Long id) {
        respond stationService.get(id)
    }

    def create() {
        respond new Station(params)
    }

    def save(Station station) {
        if (station == null) {
            notFound()
            return
        }

        try {
            stationService.save(station)
        } catch (ValidationException e) {
            respond station.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'station.label', default: 'Station'), station.id])
                redirect station
            }
            '*' { respond station, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond stationService.get(id)
    }

    def update(Station station) {
        if (station == null) {
            notFound()
            return
        }

        try {
            stationService.save(station)
        } catch (ValidationException e) {
            respond station.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'station.label', default: 'Station'), station.id])
                redirect station
            }
            '*'{ respond station, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        stationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'station.label', default: 'Station'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'station.label', default: 'Station'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
