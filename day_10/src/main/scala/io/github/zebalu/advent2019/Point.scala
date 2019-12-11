package io.github.zebalu.advent2019

import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec
import scala.collection.mutable.Set
import scala.collection.mutable.HashSet

class Point(val x: Int, val y: Int) {

  private var _skyMap: Map[Point, String] = null
  private var _asteroids: List[Point] = null
  private var _visibleAsteroids = -1

  def skyMap = _skyMap

  def skyMap_=(sMap: Map[Point, String]): Unit = {
    _visibleAsteroids = -1
    _skyMap = sMap
  }

  def asteroids = _asteroids
  def asteroids_=(ast: List[Point]): Unit = {
    _visibleAsteroids = -1
    _asteroids = (ast)
  }

  def groupedByExactAngles(): Iterable[List[Point]] = {
    asteroids.filter(_ != this).groupBy {
      case p =>
        val factor = greatestCommonDenomitor(x - p.x, y - p.y)
        new Point((x - p.x) / factor, (y - p.y) / factor)
    }.values
  }
  
  def calculateVisibleAsteroids(): Int = {
    if (_visibleAsteroids > -1) {
      _visibleAsteroids
    } else {
      _visibleAsteroids = 0
      var normalDirectionVectorSet = HashSet.empty[(Int, Int)]
      for (i <- _asteroids) {
        if (i != this) {
          val directionVector = (x-i.x, y-i.y)
          val factor = greatestCommonDenomitor(directionVector._1, directionVector._2)
          val normalVector = (directionVector._1/factor, directionVector._2/factor)
          normalDirectionVectorSet+=normalVector
        }
      }
      _visibleAsteroids=normalDirectionVectorSet.size
      _visibleAsteroids
    }
  }

  @tailrec
  private def greatestCommonDenomitor(a: Int, b: Int): Int = {
    if (b == 0) math.abs(a) else greatestCommonDenomitor(math.abs(b), math.abs(a % b))
  }
  
  override def hashCode(): Int = {
    x * 37 + y * 123
  }
  override def equals(obj: Any): Boolean = {
    obj match {
      case p: Point => p.isInstanceOf[Point] && x == p.x && y == p.y
      case _ => false
    }
  }

  override def toString(): String = {
    "(" + x + ", " + y + ")"
  }
}