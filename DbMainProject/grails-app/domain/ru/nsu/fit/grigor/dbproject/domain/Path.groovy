package ru.nsu.fit.grigor.dbproject.domain

class Path {

    Station startStation
    Station endStation
    Date productionDate

    static constraints = {
        startStation validator: { if (it.id == endStation.id) return ["start must not be end"] }
        productionDate validator: { if (it.after(new Date())) return ["invalid date"] }
    }

    @Override
    String toString() {
        return "from $startStation to $endStation"
    }
}
