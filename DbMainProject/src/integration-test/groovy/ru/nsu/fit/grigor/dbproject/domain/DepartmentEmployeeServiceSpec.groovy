package ru.nsu.fit.grigor.dbproject.domain

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DepartmentEmployeeServiceSpec extends Specification {

    DepartmentEmployeeService departmentEmployeeService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DepartmentEmployee(...).save(flush: true, failOnError: true)
        //new DepartmentEmployee(...).save(flush: true, failOnError: true)
        //DepartmentEmployee departmentEmployee = new DepartmentEmployee(...).save(flush: true, failOnError: true)
        //new DepartmentEmployee(...).save(flush: true, failOnError: true)
        //new DepartmentEmployee(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //departmentEmployee.id
    }

    void "test get"() {
        setupData()

        expect:
        departmentEmployeeService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeService.list(max: 2, offset: 2)

        then:
        departmentEmployeeList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        departmentEmployeeService.count() == 5
    }

    void "test delete"() {
        Long departmentEmployeeId = setupData()

        expect:
        departmentEmployeeService.count() == 5

        when:
        departmentEmployeeService.delete(departmentEmployeeId)
        sessionFactory.currentSession.flush()

        then:
        departmentEmployeeService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DepartmentEmployee departmentEmployee = new DepartmentEmployee()
        departmentEmployeeService.save(departmentEmployee)

        then:
        departmentEmployee.id != null
    }
}
