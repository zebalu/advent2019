package io.github.zebalu.advent2019

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap
import scala.io.Source
import scala.util.Success
import scala.util.Failure
import scala.util.Try
import scala.io.BufferedSource

class ReactionGraph {

  var productReactionMap: Map[String, ReactionNode] = new HashMap[String, ReactionNode]

  def findReactionForProduct(product: String): ReactionNode = productReactionMap(product)

  def read(name: String): Unit = {
    var res: BufferedSource = null
    try {
      res = Source.fromResource(name)
      res.getLines().foreach(s => {
        val a = s.split(" => ")
        val prd = convert(a(1))
        val node = new ReactionNode(prd._1, prd._2)
        a(0).split(", ").map(convert).foreach(r => node.reactants.put(r._1, r._2))
        productReactionMap.put(node.productName, node)
      })
    } finally {
      if (res != null) {
        res.close()
      }
    }
  }
  
  private def convert(s: String): (String, Int) = {
    val a = s.split(" ")
    (a(1), a(0).toInt)
  }

}