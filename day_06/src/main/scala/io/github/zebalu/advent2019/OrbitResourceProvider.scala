package io.github.zebalu.advent2019

import java.io.InputStreamReader
import scala.util.Success
import scala.util.Failure
import java.io.BufferedReader
import scala.util.Try
import java.util.stream.Collectors
import scala.collection.JavaConverters._
import scala.io.Source
import scala.collection.mutable.ArrayBuffer

class OrbitResourceProvider(rName: String) {
  val orbits: Array[String] = {
    val rr = Source.fromInputStream(getClass.getResourceAsStream(rName))
    var buff = new ArrayBuffer[String]
    for(line <- rr.getLines()) {
      buff += line
    }
    rr.close()
    buff.toArray
  }
}