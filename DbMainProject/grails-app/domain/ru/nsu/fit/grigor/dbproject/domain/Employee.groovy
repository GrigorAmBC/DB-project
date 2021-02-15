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

    static hasMany = [departments: Department]

    static hasOne = HealthCheck

    static constraints = {
        name blank: false, size: 2..15
        position nullable: true, size: 2..40
        children_count min: 0, max: 20
        salary min: 1, max: 1000000
        age min: 18
        employmentDate validator: {val -> validateEmploymentDate(val)}
        team nullable: true
    }

    private static validateEmploymentDate(Date date) {
        if (date.after(new Date()))
            return ["invalid date"]
    }

    @Override
    String toString() {
        return "$name, $age y.o."
    }
}
