package io.github.zebalu.advent2019

import scala.collection.immutable.List
import scala.io.Source

object MoonReader {
  def readMoons(name: String): List[Moon] = {
    val resource = Source.fromResource(name)
    var res = List.empty[Moon]
    try {
      for (l <- resource.getLines()) {
        val ints = l.replaceAll("<", "").replaceAll(">", "").split(",").map(_.trim()).map(_.substring(2)).map(_.toInt)
        val moon = new Moon(new Coords(ints(0), ints(1), ints(2)), new Coords(0, 0, 0))
        res = res ++ List(moon)
      }
    } finally {
      resource.close()
    }
    res
  }
}