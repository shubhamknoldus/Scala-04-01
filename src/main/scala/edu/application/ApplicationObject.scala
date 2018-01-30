package edu.application

import java.io.File

import edu.operation.FileOperations
import org.apache.log4j.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
// scalastyle:off
object Values{
  val threadTime = 10000
}
// scalastyle:on
object ApplicationObject {
  def main(args: Array[String]): Unit = {
    val obj = new FileOperations
//    val file = new File("/home/knoldus/Desktop/folder1")
    val file = new File("/home/knoldus/Desktop/KIP JAN/GIT")
    val logger = Logger.getLogger(this.getClass)
    obj.printFileStructure(file) onComplete {
      case Success(success) => logger.info(s"$success")
      case Failure(failure) => logger.info(s"$failure")
    }
    Thread.sleep(Values.threadTime)
  }
}
