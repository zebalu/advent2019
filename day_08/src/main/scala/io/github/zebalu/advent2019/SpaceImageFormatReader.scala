package io.github.zebalu.advent2019

import scala.io.Source
import scala.collection.mutable.ListBuffer

object SpaceImageFormatReader {
  def digitFromResource(name: String): List[Int] = {
    val rr = Source.fromInputStream(getClass.getResourceAsStream(name))
    var buff = new ListBuffer[Int]
    for(line <- rr.getLines()) {
      for(i <- 0.to(line.size-1)) {
        buff += Integer.parseInt(line.substring(i, i+1))
      }
    }
    rr.close()
    buff.toList
  }
}