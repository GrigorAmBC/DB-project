package ru.nsu.fit.grigor.railways

class Person {

    String name
    Gender gender

    static constraints = {
        name blank: false, size: 2..20
        gender nullable: false
    }
}
