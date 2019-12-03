package io.github.zebalu.advent2019

import scala.util.Try
import java.io.BufferedReader
import java.io.InputStreamReader
import scala.util.Success
import scala.util.Failure
import java.util.stream.Collectors
import scala.collection.mutable.ListBuffer

object WireReader {
  val wires = {
    Try(new BufferedReader(new InputStreamReader(WireReader.getClass().getResourceAsStream("/puzzle3_input.txt")))) match {
      case Success(br) => {
        val lines = br.lines().map[Array[String]](_.split(",")).map[Wire](array=> new Wire(array)).collect(Collectors.toList())
        br.close()
        var lb = new ListBuffer[Wire]
        lines.forEach(w => lb += w)
        lb.toList
      }
      case Failure(e) => {
        throw e
      }
    }
  }
}