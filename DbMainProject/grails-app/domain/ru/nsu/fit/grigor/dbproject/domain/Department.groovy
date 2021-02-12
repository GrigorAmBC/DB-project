package ru.nsu.fit.grigor.dbproject.domain

class Department {

    String name


    /*
    todo: many To many with employees
     */
    static constraints = {
        name blank: false, size: 5..40
    }
}
