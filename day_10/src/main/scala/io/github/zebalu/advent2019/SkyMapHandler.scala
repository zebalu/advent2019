package io.github.zebalu.advent2019

import scala.collection.mutable.Map

class SkyMapHandler(source: String) {
  val asteroids = AsteroidReader.asteroids(source)

  val mostVisibleAsteroid = asteroids.max((left: Point, right: Point) => left.calculateVisibleAsteroids() - right.calculateVisibleAsteroids())

  val maxVisible = mostVisibleAsteroid.calculateVisibleAsteroids()

  /**
   * this genius ordering idea is coming from: https://github.com/kbielefe/advent-of-code/blob/master/src/main/scala/2019/10.scala
   * I don't know enough math for this. :(
   */
  val destructionOrder = {
    val sortedByDistance = mostVisibleAsteroid.groupedByExactAngles()
      .map { _.toList.sortBy { case p => math.abs(mostVisibleAsteroid.x - p.x) + math.abs(mostVisibleAsteroid.y - p.y) } } //math.sqrt(math.pow(mostVisibleAsteroid.x - p.x, 2) + math.pow(mostVisibleAsteroid.y - p.y, 2)) } }

    val sortedByApproxAngle = sortedByDistance.toList.sortBy { group =>
      val p = group.head
      val angle = math.atan2((mostVisibleAsteroid.y - p.y).toDouble, (p.x - mostVisibleAsteroid.x).toDouble)
      val rotated = math.Pi / 2.0 - angle
      val normalized = (4.0 * math.Pi + rotated) % (2.0 * math.Pi)
      normalized
    }

    val dummyPointForTransposePadTo = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE)
    val maxSize = sortedByApproxAngle.map(_.size).max
    val padded = sortedByApproxAngle.map(_.padTo(maxSize, dummyPointForTransposePadTo))
    val transposed = padded.transpose.map(_.filter(_ != dummyPointForTransposePadTo))
    transposed.flatten
  }

}
