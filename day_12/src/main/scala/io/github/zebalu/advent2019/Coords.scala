package io.github.zebalu.advent2019

class Coords(var x: Int, var y: Int, var z: Int) {
  def +(that: Coords) = new Coords(x + that.x, y + that.y, z + that.z)
  def /(factor: Int) = new Coords(x / factor, y / factor, z / factor)
  def sum(): Int = Math.abs(x) + Math.abs(y) + Math.abs(z)
  def copy(): Coords = new Coords(x, y, z)

  override def toString(): String = {
    s"<x=${x}, y=${y}, z=${z}>"
  }

  override def hashCode(): Int = x + 31 * y + 107 * z
  override def equals(other: Any): Boolean = {
    if (this == other) {
      true
    } else {
      other match {
        case that: Coords => that.isInstanceOf[Coords] && x == that.x && y == that.y && z == that.z
        case _ => false
      }
    }
  }

}