package dbproject

import ru.nsu.fit.grigor.railways.Gender
import ru.nsu.fit.grigor.railways.GenderService
import ru.nsu.fit.grigor.railways.Person
import ru.nsu.fit.grigor.railways.PersonService

class BootStrap {

    PersonService personService

    def init = { servletContext ->
        Gender male = new Gender(name: "male").save()
        Gender female = new Gender(name: "female").save()
        Gender bi = new Gender(name: "bi").save()
        new Gender(name: "lesbian").save()
        new Person(name: "grigor", gender: male).save()
        new Person(name: "anna", gender: female).save()
        new Person(name: "george", gender: male).save()
    }
    def destroy = {
    }
}
