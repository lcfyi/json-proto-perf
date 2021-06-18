package formats

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.main.{JsonSchema, JsonSchemaFactory}
import utils.RandUtils

object JsonSimple extends Model {
  val schema: JsonSchema = JsonSchemaFactory
    .byDefault()
    .getJsonSchema(
      JsonLoader.fromString(
        """
          |{
          |    "$schema": "http://json-schema.org/draft-04/schema",
          |    "id": "http://example.com/example.json",
          |    "required": [
          |        "id"
          |    ],
          |    "properties": {
          |        "id": {
          |            "id": "#/properties/id",
          |            "minimum": 100,
          |            "type": "integer"
          |        }
          |    }
          |}
          |""".stripMargin))

  def generator(length: Int, dataLength: Int): IndexedSeq[JsonNode] = {
    for (_ <- 0 until length) yield {
      val objectMapper = new ObjectMapper()
      val parentNode = objectMapper.createObjectNode()
      parentNode.put("id", RandUtils.getRandomNumber(0, dataLength))
      parentNode
    }
  }

  def validate(simple: Any): Unit = {
    schema.validate(simple.asInstanceOf[JsonNode])
  }
}
