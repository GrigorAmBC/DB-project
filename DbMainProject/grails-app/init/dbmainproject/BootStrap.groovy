package dbmainproject

import ru.nsu.fit.grigor.dbproject.domain.Department
import ru.nsu.fit.grigor.dbproject.domain.DepartmentService
import ru.nsu.fit.grigor.dbproject.domain.Team
import ru.nsu.fit.grigor.dbproject.domain.TeamService

class BootStrap {

    def init = { servletContext ->
        Department d1 = new Department(name: "Builders").save()
        Department d2 = new Department(name: "Healers").save()
        Department d3 = new Department(name: "HR").save()
        Department d4 = new Department(name: "CHOP").save()

        new Team(department: d2, createdAt: getDate(2015 - 1900, 2, 2), budget: 10000, name: "team2", isWorking: true).save()
        new Team(department: d1, createdAt: getDate(2020 - 1900, 11, 3), budget: 41234, name: "team3", isWorking: true).save()
        new Team(department: d3, createdAt: getDate(2005 - 1900, 12, 15), budget: 29201, name: "team4", isWorking: false).save()
        new Team(department: d4, createdAt: getDate(2000 - 1900, 7, 21), budget: 120, name: "team5", isWorking: false).save()
    }
    def destroy = {
    }

    static Date getDate(int year, int month, int date) {
        return new Date(year - 1900, month, date)
    }
}