package io.github.zebalu.advent2019

import scala.io.Source
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors
import scala.collection.JavaConverters._

object SpaceCraftModuleLoader {
  val modules = {
    Try(new BufferedReader(new InputStreamReader(SpaceCraftModuleLoader.getClass().getResourceAsStream("/puzzle1_input.txt")))) match {
      case Success(br) => {
        val lines = br.lines().collect(Collectors.toList()).asScala
        br.close()
        lines.map(line => Integer.parseInt(line)).toArray
      }
      case Failure(e) => {
        throw e
      }
    }
  }
}