package ru.nsu.fit.grigor.dbproject.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HealthCheckServiceSpec extends Specification {

    HealthCheckService healthCheckService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new HealthCheck(...).save(flush: true, failOnError: true)
        //new HealthCheck(...).save(flush: true, failOnError: true)
        //HealthCheck healthCheck = new HealthCheck(...).save(flush: true, failOnError: true)
        //new HealthCheck(...).save(flush: true, failOnError: true)
        //new HealthCheck(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //healthCheck.id
    }

    void "test get"() {
        setupData()

        expect:
        healthCheckService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<HealthCheck> healthCheckList = healthCheckService.list(max: 2, offset: 2)

        then:
        healthCheckList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        healthCheckService.count() == 5
    }

    void "test delete"() {
        Long healthCheckId = setupData()

        expect:
        healthCheckService.count() == 5

        when:
        healthCheckService.delete(healthCheckId)
        sessionFactory.currentSession.flush()

        then:
        healthCheckService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        HealthCheck healthCheck = new HealthCheck()
        healthCheckService.save(healthCheck)

        then:
        healthCheck.id != null
    }
}
