package io.github.zebalu.advent2019

import scala.collection.mutable.Set
import scala.collection.mutable.HashSet
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
import java.time.Instant
import java.time.Duration
import java.util.ArrayList

class GravitySystem(moons: List[Moon]) {

  val xHistory: Set[ArrayList[Int]] = HashSet.empty
  val yHistory: Set[ArrayList[Int]] = HashSet.empty
  val zHistory: Set[ArrayList[Int]] = HashSet.empty

  var xHistoryChanged = true
  var yHistoryChanged = true
  var zHistoryChanged = true

  def timeStep(): Unit = {
    saveStates()
    moons.foreach(_.applyGravitiy(moons))
    moons.foreach(_.move())
  }

  private def saveStates(): Unit = {
    var xBuffer = new ArrayList[Int](moons.size*2)
    var yBuffer = new ArrayList[Int](moons.size*2)
    var zBuffer = new ArrayList[Int](moons.size*2)
    moons.foreach(m => {
      if (xHistoryChanged) {
        xBuffer.add(m.position.x)
        xBuffer.add(m.velocity.x)
      }
      if (yHistoryChanged) {
        yBuffer.add(m.position.y)
        yBuffer.add(m.velocity.y)
      }
      if (zHistoryChanged) {
        zBuffer.add(m.position.z)
        zBuffer.add(m.velocity.z)
      }
    })
    if (xHistoryChanged) {
      xHistoryChanged = xHistory.add(xBuffer)
    }
    if (yHistoryChanged) {
      yHistoryChanged = yHistory.add(yBuffer)
    }
    if (zHistoryChanged) {
      zHistoryChanged = zHistory.add(zBuffer)
    }
  }

  def findFirstRepeat(): Long = {
    do {
      timeStep()
    } while (xHistoryChanged || yHistoryChanged || zHistoryChanged);
    val end = Instant.now()
    lowestCommonMultiple(List(xHistory.size.toLong, yHistory.size.toLong, zHistory.size.toLong))
  }

  private def historySize() = xHistory.size + yHistory.size + zHistory.size

  private final def lowestCommonMultiple(list: List[Long]): Long = list.foldLeft(1L) {
    (a, b) =>
      {
        b * a / greatestCommonDenomitor(a, b)
      }
  }

  @tailrec
  private def greatestCommonDenomitor(a: Long, b: Long): Long = {
    if (b == 0) a else greatestCommonDenomitor(b, a % b)
  }

  def totalEnery(): Int = moons.map(_.totalEnery()).sum
}