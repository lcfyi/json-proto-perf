package formats

import utils.RandUtils

import scala.simple.{Simple, SimpleValidator}

object ProtobufSimple extends Model {
  def generator(length: Int, dataLength: Int): IndexedSeq[Simple] = {
    for (_ <- 0 until length)
      yield Simple(
        id = RandUtils.getRandomNumber(0, dataLength)
      )
  }

  def validate(simple: Any): Unit = {
    SimpleValidator.validate(simple.asInstanceOf[Simple])
  }
}
