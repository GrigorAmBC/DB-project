package ru.nsu.fit.grigor.dbproject.domain

class Status {

    String name

    static constraints = {
        name blank: false, size: 2..20, unique: true
    }

    @Override
    String toString() {
        return name
    }
}
