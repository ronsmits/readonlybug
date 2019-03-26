package readonly.kotlin.app

import readonly.java.Person
import tornadofx.*

class MyApp : App(MainView::class)

class PersonModel(person: Person) : ItemViewModel<Person>(person) {
    val firstname = observable(person, Person::getFirstname, Person::setFirstname)
    val lastname = observable(person, Person::getLastname, Person::setLastname)
}

val personList = listOf(Person("martin", "fowler")).observable()
val personModelList = listOf(PersonModel(Person("Martin", "Fowler"))).observable()

class MainView : View() {
    override val root = borderpane {
        left<LeftView>()
        right<RightView>()
    }
}

class LeftView : View() {
    override val root = tableview(personModelList) {
        column("first name", PersonModel::firstname)
        column("last name", PersonModel::lastname)
    }
}
class RightView : View() {
    override val root = tableview(personList) {
        readonlyColumn("first name", Person::getFirstname)
        readonlyColumn("last name", Person::getLastname)
    }
}