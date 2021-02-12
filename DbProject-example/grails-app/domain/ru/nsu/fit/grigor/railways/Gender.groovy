package ru.nsu.fit.grigor.railways

class Gender {

    String name

    static constraints = {
        name size: 2..15, blank: false, unique: true
    }

    @Override
    String toString() {
        return name
    }
}
