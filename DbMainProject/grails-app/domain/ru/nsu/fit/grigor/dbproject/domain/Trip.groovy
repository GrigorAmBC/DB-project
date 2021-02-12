package ru.nsu.fit.grigor.dbproject.domain

class Trip {

    Locomotive locomotive
    Path path
    String direction
    Status status
    String category
    Date tripDate
    Boolean isAbroad

    static constraints = {
        isAbroad nullable: true
        tripDate nullable: true
        status nullable: true
        direction blank: false, size: 5..30
        category nullable: true
    }
}
