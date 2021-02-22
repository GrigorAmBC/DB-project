package ru.nsu.fit.grigor.dbproject.domain

class HealthCheck {

    Date checkDate
    Employee employee
    String result

    static constraints = {
        result blank: false, size: 0..10
        employee unique: true
        checkDate validator: { if (it.after(new Date())) return ["invalid date"] }
    }

    @Override
    String toString() {
        return result
    }
}
