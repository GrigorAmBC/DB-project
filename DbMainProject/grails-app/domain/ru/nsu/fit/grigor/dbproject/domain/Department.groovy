package ru.nsu.fit.grigor.dbproject.domain

class Department {

    String name

    static constraints = {
        name blank: false, size: 5..40, unique: true
    }

    static hasMany = [teams: Team, employees: Employee]

    static belongsTo = Employee

    @Override
    String toString() {
        return name
    }
}
