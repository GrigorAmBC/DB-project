package ru.nsu.fit.grigor.railways

import grails.gorm.services.Service

@Service(Person)
abstract class PersonService {

    abstract Person get(Serializable id)

    abstract List<Person> list(Map args)

    List<Person> listWithParams(Map args) {
        Long genderId = args.get("gender") as Long
        String personName = args.get("name")
        Gender gender = Gender.findById(genderId)

        return Person.list(args).findAll {
                gender != null && it.gender == gender || gender == null
        }.findAll {
            personName != null && it.name == personName || personName == null
        }
    }

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Person save(Person person)

}