package ru.nsu.fit.grigor.dbproject.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class StationServiceSpec extends Specification {

    StationService stationService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Station(...).save(flush: true, failOnError: true)
        //new Station(...).save(flush: true, failOnError: true)
        //Station station = new Station(...).save(flush: true, failOnError: true)
        //new Station(...).save(flush: true, failOnError: true)
        //new Station(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //station.id
    }

    void "test get"() {
        setupData()

        expect:
        stationService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Station> stationList = stationService.list(max: 2, offset: 2)

        then:
        stationList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        stationService.count() == 5
    }

    void "test delete"() {
        Long stationId = setupData()

        expect:
        stationService.count() == 5

        when:
        stationService.delete(stationId)
        sessionFactory.currentSession.flush()

        then:
        stationService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Station station = new Station()
        stationService.save(station)

        then:
        station.id != null
    }
}
