package io.github.zebalu.advent2019

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.util.Try
import scala.io.Source
import scala.util.Success
import scala.util.Failure
import io.github.zebalu.advent2019.Direction.Dir
import io.github.zebalu.advent2019.Direction.Dir
import io.github.zebalu.advent2019.Direction.Dir
import scala.collection.mutable.Stack
import scala.collection.mutable.HashSet
import scala.annotation.tailrec
import scala.collection.mutable.Set
import scala.collection.mutable.Queue
import scala.io.StdIn

class OxygenSystemFinder(computer: FeedableIntComputer) {

  var board: Map[(Long, Long), Long] = HashMap.empty[(Long, Long), Long]
  var shouldPrint = true
  var droidPosition = (0L, 0L)
  var targetPositions = Stack.empty[(Long, Long)]
  var plans = HashSet.empty[(Long, Long)]

  def draw(): Unit = {
    board.put(droidPosition, 1L)
    uncheckedNeighbours(droidPosition).foreach(it => {
      targetPositions.push(it)
      plans.add(it)
    })
    var plan = findRoute()
    var lastDir: Dir = null
    computer.setDefaultAnser(_ => {
      if (plan.isEmpty) {
        throw new IllegalStateException("I don't know what to do");
      }
      lastDir = plan.head
      plan = plan.tail
      lastDir.intCode
    })
    computer.increaseMaxRead()
    while (!plan.isEmpty) {
      lastDir = plan.head
      computer.execute()
      val dc = computer.getDiagnosticCode()
      val next: (Long, Long) = (droidPosition._1 + lastDir.difX, droidPosition._2 + lastDir.difY)
      board.put(next, dc)
      dc match {
        case 1 => {
          board.put(next, 1)
          uncheckedNeighbours(next).foreach(it => {
            plans.add(it)
            targetPositions.push(it)
          })
          droidPosition = next
          if (plan.isEmpty) {
            plan = findRoute()
          }
        }
        case 2 => {
          plan = List.empty
        }
        case 0 => {
          board.put(next, 0)
          plan = findRoute()
        }
      }
      computer.increaseMaxRead()
    }
    if (shouldPrint) {
      val sb = StringBuilder.newBuilder
      for (y <- board.keys.map(_._2).min.to(board.keys.map(_._2).max)) {
        for (x <- board.keys.map(_._1).min.to(board.keys.map(_._1).max)) {
          if (board.contains((x, y))) {
            if (x == y && x == 0) {
              //print("D")
              sb.append("D")
            } else {
              val v = board((x, y))
              v match {
                case 0 => sb.append("X") // print("X")
                case 1 => sb.append(" ") // print(".")
                case 2 => sb.append("O") // print("O")
                case _ => sb.append("?") // print(" ")
              }
            }
          } else {
            //print("?")
            sb.append("?") // 
          }
        }
        sb.append("\n")
        //println()
      }
      println(sb.toString())
      println("\n\n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ \n\n")
      //0.to(5).foreach(_ => println("\n"))
    }
  }

  def calculate(block: Long): Int = board.values.filter(_ == block).size

  def shortestPath(): Long = {
    val q = Queue.empty[List[(Long, Long)]]
    q += List((0L, 0L))
    shortestPathR(HashSet.empty[(Long, Long)], q)
  }

  @tailrec
  private def shortestPathR(visited: Set[(Long, Long)], pathes: Queue[List[(Long, Long)]]): Long = {
    val top = pathes.head
    val topOption = board.get(top.last)
    val np = pathes.drop(1)
    if (topOption.isDefined && !visited.contains(top.last)) {
      if (topOption.get == 2) {
        top.size - 1
      } else if (topOption.get == 1) {
        var a: Queue[List[(Long, Long)]] = pathes
        neighbours(top.last).map(it => top ::: List(it)).foreach(it => {
          a += it
        })
        shortestPathR(visited ++ List(top.last), a)
      } else {
        shortestPathR(visited ++ List(top.last), np)
      }
    } else {
      shortestPathR(visited ++ List(top.last), np)
    }
  }

  def fillTime(): Long = {
    val spaces = findWithValue(1L)
    val oxygens = findWithValue(2L)
    var newAreas: List[(Long, Long)] = oxygens.toList
    var count = 0L
    while (!spaces.isEmpty) {
      newAreas = newAreas.map(it => neighbours(it)).flatten.filter(it => !oxygens.contains(it) && board.contains(it) && board(it) == 1)
      newAreas.foreach(it => {
        spaces.remove(it)
        oxygens.add(it)
      })
      count += 1L
    }
    count
  }

  def findWithValue(value: Long): Set[(Long, Long)] = {
    var result = HashSet.empty[(Long, Long)]
    for (y <- board.keys.map(_._2).min.to(board.keys.map(_._2).max)) {
      for (x <- board.keys.map(_._1).min.to(board.keys.map(_._1).max)) {
        val opt = board.get((x, y))
        if (opt.isDefined && opt.get == value) {
          result += ((x, y))
        }
      }
    }
    result
  }

  def findRoute(): List[Dir] = {
    var route = List.empty[Dir]
    var l = neighbours(droidPosition).filter(it => (board.contains(it) && board(it) == 1L) || it.equals(targetPositions.top))
    targetPositions.pop()
    var r = (false, List.empty[(Long, Long)])
    val i = l.iterator
    while (!r._1 && i.hasNext) {
      val s = List(i.next())
      r = findRouteR(s)
    }
    if (r._1) {
      postToDir(droidPosition, r._2.head, r._2.tail, List.empty[Dir])
    } else {
      List.empty[Dir]
    }
  }

  private def findRouteR(path: List[(Long, Long)]): (Boolean, List[(Long, Long)]) = {
    if (path.isEmpty) {
      (false, path)
    } else {
      val last = path.last
      if (board.contains(last)) {
        if (board(last) == 0) {
          (false, path)
        } else {
          var found = false
          var nPath: List[(Long, Long)] = null
          val nexts = neighbours(last).filter(it => !path.contains(it) && (!board.contains(it) || (board.contains(it) && board(it) == 1L)))
          for (i <- nexts) {
            if (!found) {
              val pc = path ++ List(i)
              val nc = findRouteR(pc)
              found = nc._1
              nPath = nc._2
            }
          }
          (found, nPath)
        }
      } else {
        (true, path)
      }
    }
  }

  @tailrec
  private def postToDir(last: (Long, Long), curr: (Long, Long), tail: List[(Long, Long)], result: List[Dir]): List[Dir] = {
    if (tail == null || tail.isEmpty) {
      result ++ List(Direction.getDirFromPos(last, curr))
    } else {
      postToDir(curr, tail.head, tail.tail, result ++ List(Direction.getDirFromPos(last, curr)))
    }
  }

  def uncheckedNeighbours(position: (Long, Long)): List[(Long, Long)] = neighbours(position).filter(it => !board.contains(it) && !plans.contains(it))

  def neighbours(position: (Long, Long)): List[(Long, Long)] = List(Direction.UP.move(position), Direction.RIGHT.move(position), Direction.DOWN.move(position), Direction.LEFT.move(position))

}

object DroidCodeReader {
  def read(name: String): Array[Long] = {
    Try(Source.fromResource(name)) match {
      case Success(src) => src.getLines().map(_.split(",")).flatten.map(_.toLong).toArray
      case Failure(e) => throw new IllegalArgumentException("Could not work with " + name, e)
    }
  }
}