package ru.nsu.fit.grigor.dbproject.domain

class Team {

    Department department
    Date createdAt

    static constraints = {
        createdAt nullable: true
    }
}
