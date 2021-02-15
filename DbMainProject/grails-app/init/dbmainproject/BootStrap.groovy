package dbmainproject

import ru.nsu.fit.grigor.dbproject.domain.Department
import ru.nsu.fit.grigor.dbproject.domain.Employee
import ru.nsu.fit.grigor.dbproject.domain.Gender
import ru.nsu.fit.grigor.dbproject.domain.Locomotive
import ru.nsu.fit.grigor.dbproject.domain.Path
import ru.nsu.fit.grigor.dbproject.domain.Station
import ru.nsu.fit.grigor.dbproject.domain.Status
import ru.nsu.fit.grigor.dbproject.domain.Team
import ru.nsu.fit.grigor.dbproject.domain.Trip

class BootStrap {

    def init = { servletContext ->
        Gender male = new Gender(name: "male")
        Gender female = new Gender(name: "female")

        Department d1 = new Department(name: "Builders").save()
        Department d2 = new Department(name: "Healers").save()
        Department d3 = new Department(name: "HR").save()
        Department d4 = new Department(name: "CHOP").save()

        Team team2 = new Team(department: d2, createdAt: getDate(2015, 2, 2), budget: 10000, name: "team2", isWorking: true).save()
        Team team3 = new Team(department: d1, createdAt: getDate(2020, 11, 3), budget: 41234, name: "team3", isWorking: true).save()
        Team team4 = new Team(department: d3, createdAt: getDate(2005, 12, 15), budget: 29201, name: "team4", isWorking: false).save()
        Team team5 = new Team(department: d4, createdAt: getDate(2000, 7, 21), budget: 120, name: "team5", isWorking: false).save()

        Employee employee1 = new Employee(
                name: "Grigor", gender: male, age: 21, children_count: 4, position: "Senior manager",
                employmentDate: new Date(), salary: 210000, team: team2
        ).save()
        Employee employee2 = new Employee(
                name: "George", gender: male, age: 21, children_count: 4, position: "Chief builder",
                employmentDate: getDate(1999, 2, 21), salary: 210000
        ).save()

        Station station1 = new Station(name: "Novosibirsk main").save()
        Station station2 = new Station(name: "Novosibirsk south").save()
        Station station3 = new Station(name: "Moon").save()
        Station station4 = new Station(name: "Siberia").save()

        Path path1 = new Path(startStation: station1, endStation: station3, productionDate: getDate(1965, 4, 15)).save()
        Path path2 = new Path(startStation: station2, endStation: station4, productionDate: getDate(1979, 6, 5)).save()
        Path path3 = new Path(startStation: station1, endStation: station4, productionDate: getDate(1993, 9, 9)).save()

        Locomotive locomotive1 = new Locomotive(name: "Siberian bear", locomotiveTeam: team2, repairTeam: team3, station: station1,
                productionDate: getDate(2001, 4, 3), path: path1).save()

        Status status1 = new Status(name: "outdated").save()
        Status status2 = new Status(name: "actual").save()

        Trip trip = new Trip(locomotive: locomotive1, path: path1, direction: "Siberia", status: status2).save()
    }
    def destroy = {
    }

    static Date getDate(int year, int month, int date) {
        return new Date(year - 1900, month, date)
    }
}