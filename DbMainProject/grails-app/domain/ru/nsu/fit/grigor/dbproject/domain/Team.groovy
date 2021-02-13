package ru.nsu.fit.grigor.dbproject.domain

class Team {

    Department department
    Date createdAt
    String name
    Boolean isWorking
    Integer budget

    static constraints = {
        createdAt nullable: true
        name blank: false, size: 2..50
        budget min: 0, max: 10000000
    }

    static belongsTo = [department: Department]

    @Override
    String toString() {
        return "$name, ${department.name}"
    }
}
