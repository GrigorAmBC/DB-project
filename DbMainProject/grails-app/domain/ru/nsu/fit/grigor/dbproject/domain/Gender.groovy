package ru.nsu.fit.grigor.dbproject.domain

class Gender {

    String name

    static constraints = {
        name blank: false, size: 2..10, unique: true
    }

    @Override
    String toString() {
        return name
    }
}
