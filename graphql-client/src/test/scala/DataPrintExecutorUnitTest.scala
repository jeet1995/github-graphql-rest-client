import com.graphql.client.executor.DataPrintExecutor
import com.graphql.client.models._
import org.scalatest.FunSuite

class DataPrintExecutorUnitTest extends FunSuite {

  val viewer = new Viewer("test", "test")

  val repository = new Repository("04-Sep-2019", 129, null, new CommitCommentConnection(new Array[CommitComment](2)), new RepositoryOwner("login"));
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
