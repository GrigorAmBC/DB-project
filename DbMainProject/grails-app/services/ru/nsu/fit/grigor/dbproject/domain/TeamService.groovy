package ru.nsu.fit.grigor.dbproject.domain

import grails.gorm.services.Service

import java.text.SimpleDateFormat

@Service(Team)
abstract class TeamService {

    abstract Team get(Serializable id)

    abstract List<Team> list(Map args)

    List<Team> listWithParams(Map args) {
        Date fromDate = parseDate(args.get("fromDate"))
        Date toDate = parseDate(args.get("toDate"))

        Long departmentId = args.get("department") as Long
        if (departmentId < 0) {
            departmentId = null
        }
        String name = args.get("name")
        if (name.toString().empty) {
            name = null
        }
        String isWorking = args.get("working")
        if (isWorking != null && !(isWorking == "true" || isWorking == "false")) {
            isWorking = null
        }
        Long fromBudget = null
        if (!args.get("fromBudget").toString().empty) {
            fromBudget = args.get("fromBudget") as Long
        }
        Long toBudget = null
        if (!args.get("toBudget").toString().empty) {
            toBudget = args.get("toBudget") as Long
        }
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
        }.findAll {
            (fromDate != null && it.createdAt.after(fromDate) || fromDate == null)
        }.findAll {
            (toDate != null && it.createdAt.before(toDate) || toDate == null)
        }
    }

    abstract Long count()

    abstract void delete(Serializable id)

    abstract Team save(Team team)


    private static Date parseDate(Object stringObject) {
        if (stringObject == null || stringObject.toString().empty) {
            return null
        }
        try {
            String pattern = "yyyy-MM-dd"
            return new SimpleDateFormat(pattern).parse(stringObject.toString())
        } catch(Exception ignored) {
            return null
        }
    }
}