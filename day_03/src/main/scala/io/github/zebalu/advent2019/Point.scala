package io.github.zebalu.advent2019

import scala.collection.mutable.ListBuffer

class Point(x: Int, y: Int) extends Ordered[Point] {
  val getX: Int = x

  val getY: Int = y

  val step: String => Point = (stepStr: String) => {
    val direction = stepStr.substring(0, 1)
    val count = Integer.parseInt(stepStr.substring(1, stepStr.length()))
    direction match {
      case "U" => new Point(x, y - count)
      case "R" => new Point(x + count, y)
      case "D" => new Point(x, y + count)
      case "L" => new Point(x - count, y)
      case _ => throw new IllegalArgumentException("what is this: " + stepStr)
    }
  }
  
  val add: Point => Point = (other: Point) => {
    new Point(x+other.getX, y+other.getY)
  }
  
  val path: String => List[Point] = (stepStr: String) => {
    val direction = stepStr.substring(0, 1)
    val count = Integer.parseInt(stepStr.substring(1, stepStr.length()))
    val diffPoint = direction match {
      case "U" => new Point(0, -1)
      case "R" => new Point(1, 0)
      case "D" => new Point(0, 1)
      case "L" => new Point(-1, 0)
      case _ => throw new IllegalArgumentException("what is this: " + stepStr)
    }
    var collector = new ListBuffer[Point]
    var point = new Point(x,y)
    for(i <- 1 to count) {
      point = point.add(diffPoint)
      collector += point
    }
    collector.toList
  }

  val distance: Int = Math.abs(x) + Math.abs(y)

  def compare(that: Point): Int = distance - that.distance
  
  override def toString(): String = {
    "x: "+x+", y: "+y
  }
  
  override def equals(other: Any): Boolean = {
    if(other == null || other.getClass() != classOf[Point]) {
      false
    } else {
      val otherPoint: Point = other.asInstanceOf[Point]
      x == otherPoint.getX && y == otherPoint.getY
    }
  }
  
  override def hashCode(): Int = {
    x*31+y*107
  }

}