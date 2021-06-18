package formats

import utils.RandUtils

import scala.person.{Person, PersonValidator}
import scala.person.Person.Location

object Protobuf extends Model {
  def generator(length: Int, dataLength: Int): IndexedSeq[Person] = {
    for (_ <- 0 until length)
      yield Person(
        id = RandUtils.getRandomNumber(0, dataLength),
        email = RandUtils.getRandomSentence(dataLength),
        name = RandUtils.getRandomSentence(dataLength),
        home = Some(
          Location(
            lat = RandUtils.getRandomNumber(0, dataLength),
            lng = RandUtils.getRandomNumber(0, dataLength)
          )
        )
      )
  }

  def validate(person: Any): Unit = {
    PersonValidator.validate(person.asInstanceOf[Person])
  }
}
