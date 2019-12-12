package io.github.zebalu.advent2019

import scala.collection.mutable.Set
import scala.collection.mutable.HashSet

class Moon(var position: Coords, var velocity: Coords) {
  def applyGravitiy(moons: List[Moon]): Unit = {
    moons.filter(_ != this).foreach(m => {
      velocity.x += calculateChange(position.x, m.position.x)
      velocity.y += calculateChange(position.y, m.position.y)
      velocity.z += calculateChange(position.z, m.position.z)
    })
  }

  def move(): Unit = {
    position += velocity
  }
  
  def potentialEnery(): Int = position.sum()
  def kineticEnery(): Int = velocity.sum()
  def totalEnery(): Int = potentialEnery() * kineticEnery()

  override def toString(): String = s"pos=${position}, vel=$velocity pot=${potentialEnery()}, kin=${kineticEnery()}, tot=${totalEnery()}"

  private def calculateChange(mine: Int, their: Int): Int = math.signum(their - mine)
}