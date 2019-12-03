package io.github.zebalu.advent2019

import scala.collection.mutable.ListBuffer

class Wire(path: Array[String]) {
  
  val points: List[Point] = {
    var list = new ListBuffer[Point]
    var lastPoint: Point = new Point(0,0)
    for(step <- path) {
      var nextPoint = lastPoint.step(step)
      list ++= lastPoint.path(step)
      lastPoint=nextPoint
    }
    list.toList
  }
  
  private val pointSet: Set[Point] = points.toSet
  
  val distance: Wire => Int = (other: Wire) => {
    commonPoints(other).min.distance
  }
  
  val bestSteps: Wire => Int = (other: Wire) => {
    commonPoints(other).map(p=>stepsToReach(p)+other.stepsToReach(p)).min
  }
  
  val stepsToReach: Point => Int = p => {
    if(pointSet.contains(p)) {
      points.indexOf(p)+1
    } else {
      -1
    }
  }
  
  private val commonPoints: Wire => List[Point] = (other: Wire) => {
    var result = new ListBuffer[Point]
    for(point <- other.points) {
      if(pointSet.contains(point)) {
        result += point
      }
    }
    result.toList
  }
  
  
}