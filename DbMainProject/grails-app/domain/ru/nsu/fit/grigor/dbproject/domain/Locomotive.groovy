package ru.nsu.fit.grigor.dbproject.domain

class Locomotive {

    Team locomotiveTeam
    Team repairTeam
    Station station
    Date productionDate
    String name
    Path path
    Integer rideCount
    Integer rideCountAfterRepair

    static constraints = {
        name size: 2..40, blank: false
        path nullable: true
        locomotiveTeam unique: true
        rideCount min: 0, nullable: true
        rideCountAfterRepair min: 0, nullable: true
    }

    @Override
    String toString() {
        return "$name, station: $station"
    }
}
