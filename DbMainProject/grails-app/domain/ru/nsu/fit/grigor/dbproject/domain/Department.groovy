package ru.nsu.fit.grigor.dbproject.domain

class Department {

    String name

    static constraints = {
        name blank: false, size: 1..40, unique: true
    }

    static belongsTo = Employee

    @Override
    String toString() {
        return name
    }
}
