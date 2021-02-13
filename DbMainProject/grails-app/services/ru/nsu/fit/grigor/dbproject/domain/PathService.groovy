package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

@Service(Path)
interface PathService {

    Path get(Serializable id)

    List<Path> list(Map args)

    Long count()

    void delete(Serializable id)

    Path save(Path path)

}