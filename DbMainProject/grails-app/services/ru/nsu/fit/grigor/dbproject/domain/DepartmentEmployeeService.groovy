package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

@Service(DepartmentEmployee)
interface DepartmentEmployeeService {

    DepartmentEmployee get(Serializable id)

    List<DepartmentEmployee> list(Map args)

    Long count()

    void delete(Serializable id)

    DepartmentEmployee save(DepartmentEmployee departmentEmployee)

}