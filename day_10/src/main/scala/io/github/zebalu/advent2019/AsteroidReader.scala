package io.github.zebalu.advent2019

import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

object AsteroidReader {
  
  def asteroids(name: String): List[Point] = {
    val rr = Source.fromInputStream(getClass.getResourceAsStream(name))
    var lbuff = ListBuffer.empty[Point]
    var y = 0
    for(line <- rr.getLines()) {
      for(x <- 0.to(line.size-1)) {
        val char = line.substring(x, x+1)
        val point = new Point(x,y)
        if("#".equals(char)) {
          lbuff += point
        }
      }
      y+=1
    }
    rr.close()
    val list = lbuff.toList
    for(i<-lbuff) {
      i.asteroids=list
    }
    list
  }
}