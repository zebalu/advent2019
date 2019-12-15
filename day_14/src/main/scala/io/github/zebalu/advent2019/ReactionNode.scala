package io.github.zebalu.advent2019

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

class ReactionNode(val productName: String, val productCount: Long) {

  var reactants: Map[String, Long] = HashMap.empty[String, Long]

  def getReactansMultiplied(multiplier: Long): List[(String, Long)] = reactants.toList.map({ case (k, v) => (k, v * multiplier) })
}