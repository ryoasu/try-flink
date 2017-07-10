import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._

import scala.util.{Failure, Success, Try}

/**
  * Created by ryoasu on 2017/07/09.
  */
object APIRequestLogCount {

  case class AccessLog(api: String, version: String, recommendId: String, count: Long = 1)

  def main(args: Array[String]): Unit = {
    // the port to connect to
    val port: Int = Try { ParameterTool.fromArgs(args).getInt("port") } match {
      case Success(v) => v
      case Failure(e) =>
        System.err.println("No port specified. Please run 'APIRequestLogCount --port <port>'")
        return
    }

    // get the execution environment
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // get input data by connecting to the socket
    val text = env.socketTextStream("localhost", port, '\n')

    val recommendIdCounts = text
      .map { req =>
        val segments = req.split("/").tail.toList
        AccessLog(segments(0), segments(1), segments(2))
      }
      .keyBy("recommendId")
      .sum("count")

    recommendIdCounts.print().setParallelism(1)
    env.execute("Count API request log")
  }
}
