package ru.nsu.fit.grigor.railways

import grails.gorm.services.Service

@Service(Person)
interface PersonService {

    Person get(Serializable id)

    List<Person> list(Map args)

    Long count()

    void delete(Serializable id)

    Person save(Person person)

}