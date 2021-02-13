package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

@Service(Station)
interface StationService {

    Station get(Serializable id)

    List<Station> list(Map args)

    Long count()

    void delete(Serializable id)

    Station save(Station station)

}