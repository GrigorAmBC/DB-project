package ru.nsu.fit.grigor.dbproject.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LocomotiveServiceSpec extends Specification {

    LocomotiveService locomotiveService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Locomotive(...).save(flush: true, failOnError: true)
        //new Locomotive(...).save(flush: true, failOnError: true)
        //Locomotive locomotive = new Locomotive(...).save(flush: true, failOnError: true)
        //new Locomotive(...).save(flush: true, failOnError: true)
        //new Locomotive(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //locomotive.id
    }

    void "test get"() {
        setupData()

        expect:
        locomotiveService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Locomotive> locomotiveList = locomotiveService.list(max: 2, offset: 2)

        then:
        locomotiveList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        locomotiveService.count() == 5
    }

    void "test delete"() {
        Long locomotiveId = setupData()

        expect:
        locomotiveService.count() == 5

        when:
        locomotiveService.delete(locomotiveId)
        sessionFactory.currentSession.flush()

        then:
        locomotiveService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Locomotive locomotive = new Locomotive()
        locomotiveService.save(locomotive)

        then:
        locomotive.id != null
    }
}
