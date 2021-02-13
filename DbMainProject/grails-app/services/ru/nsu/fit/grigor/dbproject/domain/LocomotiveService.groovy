package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

@Service(Locomotive)
interface LocomotiveService {

    Locomotive get(Serializable id)

    List<Locomotive> list(Map args)

    Long count()

    void delete(Serializable id)

    Locomotive save(Locomotive locomotive)

}