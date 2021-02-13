package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

@Service(Team)
abstract class TeamService {

    abstract Team get(Serializable id)

    abstract List<Team> list(Map args)

    List<Team> listWithParams(Map args) {
        Long departmentId = args.get("department") as Long
        String name = args.get("name")
//        Date fromDate = new Date(args.get("fromDate").toString() ?: null)//todo: right?
//        Date toDate = new Date(args.get("toDate").toString())//todo: right?
        String isWorking = args.get("working")
        Long fromBudget = args.get("fromBudget") as Long
        Long toBudget = args.get("toBudget") as Long
        if (fromBudget != null && toBudget != null && fromBudget > toBudget) {
            throw new IllegalArgumentException("budget FROM must be less than or equal budget TO")
        }

        return Team.list(args).findAll {
            (name != null && it.name == name || name == null)
        }.findAll {
            (isWorking != null && it.isWorking.toString() == isWorking || isWorking == null)
        }.findAll {
            (departmentId != null && it.department.id == departmentId || departmentId == null)
        }.findAll {
            (fromBudget != null && it.budget >= fromBudget || fromBudget == null)
        }.findAll {
            (toBudget != null && it.budget <= toBudget || toBudget == null)
        }/*.findAll {
            (fromDate != null && it.createdAt.after(fromDate) || fromDate == null)
        }.findAll {
            (toDate != null && it.createdAt.before(toDate) || toDate)
        }*/
    }

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Team save(Team team)

}