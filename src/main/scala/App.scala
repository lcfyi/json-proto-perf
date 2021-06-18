import formats.{Json, JsonSimple, Model, Protobuf, ProtobufSimple}
import utils.Benchmark

object App extends App {
  def _runner(format: Model, length: Int): Seq[Long] =
    format.generator(length, 100).map { p =>
      Benchmark.timeIt {
        format.validate(p)
      }
    }

  def runBenchmark(run: Int, length: Int): Unit = {
    val protoTimes = _runner(ProtobufSimple, length)
    val jsonTimes = _runner(JsonSimple, length)
    println(s"---------- run $run, length $length ----------")
    println("Protobuf", "avg", protoTimes.sum / protoTimes.length, "max", protoTimes.max, "min", protoTimes.min)
    println("Json", "avg", jsonTimes.sum / jsonTimes.length, "max", jsonTimes.max, "min", jsonTimes.min)
  }

  println("warming up jvm...")
  _runner(ProtobufSimple, 10000)
  _runner(JsonSimple, 10000)

  for (i <- 0 to 4) {
    runBenchmark(i, Math.pow(10, i).asInstanceOf[Int])
  }
}
