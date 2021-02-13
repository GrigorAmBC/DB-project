package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

@Service(HealthCheck)
interface HealthCheckService {

    HealthCheck get(Serializable id)

    List<HealthCheck> list(Map args)

    Long count()

    void delete(Serializable id)

    HealthCheck save(HealthCheck healthCheck)

}