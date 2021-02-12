package ru.nsu.fit.grigor.railways

import grails.gorm.services.Service

@Service(Gender)
interface GenderService {

    Gender get(Serializable id)

    List<Gender> list(Map args)

    Long count()

    void delete(Serializable id)

    Gender save(Gender gender)

}