import com.graphql.client.factories.DataFactory
import com.graphql.client.models.{Repository, Viewer}
import org.json.JSONObject
import org.scalatest.FunSuite

class DataFactoryUnitTest extends FunSuite {

  test("DataFactory should return an instance of the Viewer class") {
    val jsonString = "{\n  \"data\": {\n    \"viewer\": {\n      \"login\": \"jeet1995\",\n      \"name\": \"Abhijeet Mohanty\"\n    }\n  }\n}";
    val jsonObject = new JSONObject(jsonString)

    val dataFactory = new DataFactory
    val objectCreated = dataFactory.createInstance(jsonObject)
    val objectExpected = new Viewer("Abhijeet Mohanty", "jeet1995")

    assert(objectCreated === objectExpected)
  }

  test("DataFactory should return an instance of the Repository class") {
    val jsonString = "{\n  \"data\": {\n    \"repository\": {\n      \"forkCount\": 630\n    }\n  }\n}";
    val jsonObject = new JSONObject(jsonString)

    val dataFactory = new DataFactory
    val objectCreated = dataFactory.createInstance(jsonObject)
    val objectExpected = new Repository(null, 630, null, null, null);

    assert(objectCreated === objectExpected)
  }
}
