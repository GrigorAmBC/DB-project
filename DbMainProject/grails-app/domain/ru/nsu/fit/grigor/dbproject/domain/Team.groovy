package ru.nsu.fit.grigor.dbproject.domain

class Team {

    Department department
    String name
    Date createdAt
    Boolean isWorking
    Integer budget

    static constraints = {
        name blank: false, size: 2..50
        createdAt validator: {if (it != null && it.after(new Date())) return ["invalid date"]}
        budget min: 0, max: 10000000
    }

//    static belongsTo = [department: Department]//todo: need this?

    @Override
    String toString() {
        return "$name"
    }
}
