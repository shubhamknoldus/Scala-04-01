package edu.operation

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FileOperations {

  def printFileStructure(sourceFolder: File): Future[String] = Future {
    val stringPath: String = sourceFolder.getAbsolutePath

    val directoryAndFilesSplitter = (dirObj: File) => dirObj.listFiles().toList.partition(file => !file.isFile)

    val (dList, fList) = directoryAndFilesSplitter(sourceFolder)

    def printFileStructureHelper(dirList: List[File], fileList: List[File]): List[File] = {
      dirList match {
        case head :: Nil => val (a, b) = directoryAndFilesSplitter(head)
          if (a.isEmpty) {
            b ::: fileList
          } else {
            printFileStructureHelper(dirList.drop(1) ::: a, fileList ::: b)
          }
        case head :: head2 :: tail =>
          val (a, b) = directoryAndFilesSplitter(head)
          if (a.isEmpty) {
            printFileStructureHelper(dirList.drop(1), fileList ::: b)
          } else {
            printFileStructureHelper(dirList.drop(1) ::: a, fileList ::: b)
          }
      }
    }

    printFileStructureHelper(dList, fList).map(filePath => filePath.toString.diff(stringPath)).mkString("\n")
  }
}
