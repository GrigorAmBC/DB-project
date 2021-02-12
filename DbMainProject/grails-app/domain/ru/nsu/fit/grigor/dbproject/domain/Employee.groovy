package ru.nsu.fit.grigor.dbproject.domain

class Employee {

    String name
    Gender gender
    Integer age
    Integer children_count
    String position
    Date employmentDate
    Integer salary
    Team team
    /*
    todo: many To many with departments
     */

    static constraints = {
        name blank: false, size: 2..15
        position nullable: true, size: 2..40
        children_count min: 0, max: 20
        salary min: 1
        age min: 18
    }
}
