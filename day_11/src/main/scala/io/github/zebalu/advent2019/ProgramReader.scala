package io.github.zebalu.advent2019

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object ProgramReader {
  def read(name: String): Array[Long] = {
    var arrayBuffer = new ArrayBuffer[Long]
    val src = Source.fromResource(name)
    for(line <- src.getLines()) {
      for(lng <- line.split(",")) {
        arrayBuffer += lng.toLong
      }
    }
    src.close()
    arrayBuffer.toArray
  }
}