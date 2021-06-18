package utils

object Benchmark {
  def timeIt(block: => Unit): Long = {
    val start = System.nanoTime()
    block
    System.nanoTime() - start
  }
}
