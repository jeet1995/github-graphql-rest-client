import com.graphql.client.executor.DataPrintExecutor
import com.graphql.client.models.{Repository, Viewer}
import org.scalatest.FunSuite

class DataPrintExecutorUnitTest extends FunSuite {

  val viewer = new Viewer("test", "test")
  val repository = new Repository("04-Sep-2019", 129, null, null, null);
  val dataPrintExecutor = new DataPrintExecutor

  test("DataPrintExecutor should return an instance of the Viewer class") {
    val data = dataPrintExecutor.execute(viewer)
    assert(data === viewer)
  }


  test("DataPrintExecutor should return an instance of the Repository class") {
    val data = dataPrintExecutor.execute(repository)
    assert(data === repository)
  }


}
