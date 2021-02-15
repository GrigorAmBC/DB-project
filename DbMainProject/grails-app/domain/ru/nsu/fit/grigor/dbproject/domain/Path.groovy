package ru.nsu.fit.grigor.dbproject.domain

class Path {

    Station startStation
    Station endStation
    Date productionDate

    static constraints = {
        productionDate validator: { if (it.after(new Date())) return ["invalid date"] }
    }

    @Override
    String toString() {
        return "from $startStation to $endStation"
    }
}
