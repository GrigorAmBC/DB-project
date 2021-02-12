package ru.nsu.fit.grigor.dbproject.domain

class Locomotive {

    Team locomotiveTeam//todo: make those two not equal
    Team repairTeam
    Station station
    Date productionDate
    Path path
    Integer rideCount
    Integer rideCountAfterRepair


    static constraints = {
        path nullable: true
    }
}
