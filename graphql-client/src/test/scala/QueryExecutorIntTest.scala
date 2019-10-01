import com.graphql.client.executor.QueryExecutor
import com.graphql.client.models.Viewer
import com.typesafe.config.ConfigFactory
import org.scalatest.FunSuite


class QueryExecutorIntTest extends FunSuite {

  val query = ConfigFactory.load("queries-test.conf").getConfigList("queries").get(0)
  val queryExecutor = new QueryExecutor(query.getString("description"))

  test("QueryExecutor.execute should accept a query config and return data") {
    assert(new Viewer("Abhijeet Mohanty", "jeet1995") === queryExecutor.execute(query))
  }

}
