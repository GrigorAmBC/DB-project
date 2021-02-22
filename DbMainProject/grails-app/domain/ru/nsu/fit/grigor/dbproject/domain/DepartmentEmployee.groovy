package ru.nsu.fit.grigor.dbproject.domain

class DepartmentEmployee {

    Department department
    Employee employee

    static constraints = {
        department(unique: 'employee')
    }
}
