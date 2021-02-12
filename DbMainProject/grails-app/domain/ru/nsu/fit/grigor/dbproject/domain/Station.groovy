package ru.nsu.fit.grigor.dbproject.domain

class Station {

    String name

    static constraints = {
        name blank: false, size: 2..50
    }
}
