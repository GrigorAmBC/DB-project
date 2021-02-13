package ru.nsu.fit.grigor.dbproject.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PathServiceSpec extends Specification {

    PathService pathService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Path(...).save(flush: true, failOnError: true)
        //new Path(...).save(flush: true, failOnError: true)
        //Path path = new Path(...).save(flush: true, failOnError: true)
        //new Path(...).save(flush: true, failOnError: true)
        //new Path(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //path.id
    }

    void "test get"() {
        setupData()

        expect:
        pathService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Path> pathList = pathService.list(max: 2, offset: 2)

        then:
        pathList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pathService.count() == 5
    }

    void "test delete"() {
        Long pathId = setupData()

        expect:
        pathService.count() == 5

        when:
        pathService.delete(pathId)
        sessionFactory.currentSession.flush()

        then:
        pathService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Path path = new Path()
        pathService.save(path)

        then:
        path.id != null
    }
}
