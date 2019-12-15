package io.github.zebalu.advent2019

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.annotation.tailrec

class Unreactor(val needLabel: String, val needAmount: Long, val reactions: ReactionGraph, root: String) {

  var needs: Map[String, Long] = new HashMap
  var have: Map[String, Long] = new HashMap

  needs(needLabel) = needAmount

  def findCost(): Unit = {
    while (!isAllINeedIsRoot()) {
      val need = getNotRootNeed()
      if (have.contains(need._1)) {
        use(need._1, need._2)
      } else {
        val reaction = reactions.findReactionForProduct(need._1)
        val (reactionCount, leftOver) = react(reaction, need._1, need._2)
        reaction.getReactansMultiplied(reactionCount).foreach((kv) => {
          addNeed(kv._1, kv._2)
        })
        addHave(need._1, leftOver)
        needs.remove(need._1)
      }
    }
  }

  def use(needName: String, needCount: Long) = {
    val haveCount = have(needName)
    if (needCount < haveCount) {
      have.put(needName, haveCount - needCount)
      needs.remove(needName)
    } else if (haveCount < needCount) {
      needs.put(needName, needCount - haveCount)
      have.remove(needName)
    } else if (haveCount == needCount) {
      have.remove(needName)
      needs.remove(needName)
    }
  }

  private def react(reaction: ReactionNode, needName: String, needCount: Long): (Long, Long) = {
    if (needCount < reaction.productCount) {
      (1L, reaction.productCount - needCount)
    } else {
      val reactionCount = needCount / reaction.productCount
      val leftOver = needCount % reaction.productCount
      if (leftOver > 0) {
        (reactionCount + 1, reaction.productCount - leftOver)
      } else {
        (reactionCount, leftOver)
      }
    }

  }

  def getRootNeed(): Long = {
    if (!isAllINeedIsRoot()) {
      findCost()
    }
    needs(root)
  }

  def getMaxNeedWithMaxRoot(maxRoot: Long): Long = {
    findCost()
    binSMax(maxRoot, maxRoot, 1, getRootNeed())
  }

  @tailrec
  private def binSMax(resources: Long, max: Long, min: Long, lastCalc: Long): Long = {
    if (max == min) {
      max
    } else {
      val middle = (max + min) / 2
      val calcRootNeed = new Unreactor(needLabel, middle, reactions, root).getRootNeed()
      if (calcRootNeed == lastCalc && (middle == min || middle == max)) {
        middle
      } else if (calcRootNeed < resources) {
        binSMax(resources, max, middle, calcRootNeed)
      } else if (calcRootNeed == resources) {
        middle
      } else {
        binSMax(resources, middle, min, calcRootNeed)
      }
    }
  }

  private def isAllINeedIsRoot(): Boolean = needs.size == 1 && needs.contains(root)

  private def getNotRootNeed(): (String, Long) = {
    if (isAllINeedIsRoot()) {
      throw new IllegalStateException("There is only " + root + " in needs")
    }
    val it = needs.iterator
    if (!it.hasNext) {
      throw new IllegalStateException("There is nothing I need")
    }
    val next = it.next()
    if (next._1 == root) {
      it.next()
    } else {
      next
    }
  }

  private def addNeed(needName: String, needCount: Long) = addTo(needs, needName, needCount)
  private def addHave(haveName: String, haveCount: Long) = addTo(have, haveName, haveCount)

  private def addTo(map: Map[String, Long], name: String, count: Long) {
    if (count != 0) {
      if (map.contains(name)) {
        val newVal = map(name) + count
        if (newVal != 0) {
          map.put(name, newVal)
        } else {
          map.remove(name)
        }
      } else {
        map.put(name, count)
      }
    }
  }
}