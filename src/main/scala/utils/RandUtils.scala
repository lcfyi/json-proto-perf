package utils

import scala.util.Random

object RandUtils {
  def getRandomSentence(length: Int): String = {
    Random.alphanumeric.dropWhile(_.isDigit).take(length).mkString
  }

  def getRandomNumber(min: Int, max: Int): Int = {
    min + Random.nextInt(max - min)
  }
}
