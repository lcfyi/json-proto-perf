package formats

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.main.{JsonSchema, JsonSchemaFactory}
import utils.RandUtils

object Json extends Model {
  val schema: JsonSchema = JsonSchemaFactory
    .byDefault()
    .getJsonSchema(
      JsonLoader.fromString(
        """
          |{
          |    "$schema": "http://json-schema.org/draft-04/schema",
          |    "id": "http://example.com/example.json",
          |    "required": [
          |        "id",
          |        "email",
          |        "name",
          |        "location"
          |    ],
          |    "properties": {
          |        "id": {
          |            "id": "#/properties/id",
          |            "minimum": 999,
          |            "type": "integer"
          |        },
          |        "email": {
          |            "id": "#/properties/email",
          |            "pattern": "@",
          |            "type": "string"
          |        },
          |        "name": {
          |            "id": "#/properties/name",
          |            "maxLength": 256,
          |            "type": "string"
          |        },
          |        "location": {
          |            "id": "#/properties/location",
          |            "required": [
          |                "lat",
          |                "lng"
          |            ],
          |            "type": "object",
          |            "properties": {
          |                "lat": {
          |                    "id": "#/properties/location/properties/lat",
          |                    "exclusiveMaximum": true,
          |                    "exclusiveMinimum": true,
          |                    "maximum": 90,
          |                    "minimum": -90,
          |                    "type": "number"
          |                },
          |                "lng": {
          |                    "id": "#/properties/location/properties/lng",
          |                    "exclusiveMaximum": true,
          |                    "exclusiveMinimum": true,
          |                    "maximum": 180,
          |                    "minimum": -180,
          |                    "type": "number"
          |                }
          |            }
          |        }
          |    }
          |}
          |""".stripMargin))

  def generator(length: Int, dataLength: Int): IndexedSeq[JsonNode] = {
    for (_ <- 0 until length) yield {
      val objectMapper = new ObjectMapper()
      val parentNode = objectMapper.createObjectNode()
      parentNode.put("id", RandUtils.getRandomNumber(0, dataLength))
      parentNode.put("email", RandUtils.getRandomSentence(dataLength))
      parentNode.put("name", RandUtils.getRandomSentence(dataLength))

      val childNode = objectMapper.createObjectNode()
      childNode.put("lat", RandUtils.getRandomNumber(0, dataLength))
      childNode.put("lng", RandUtils.getRandomNumber(0, dataLength))
      parentNode.set("home", childNode)

      parentNode
    }
  }

  def validate(person: Any): Unit = {
    schema.validate(person.asInstanceOf[JsonNode])
  }
}
