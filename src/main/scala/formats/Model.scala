package formats

trait Model {
  def generator(length: Int, dataLength: Int): IndexedSeq[_]
  def validate(person: Any): Unit
}
