package io.github.zebalu.advent2019

object FuelCalculator {
  val forMass = (mass: Int) => mass / 3 - 2
  val forModules = (modules: Array[Int]) => {
    var sum: Int = 0
    for (mass <- modules) {
      sum += forMass(mass)
    }
    sum
  }
  val forMassWithFuel: Int => Int = (mass: Int) => {
    val base = forMass(mass)
    if (base > 0) {
      base + forMassWithFuel(base)
    } else {
      0
    }
  }
  val forModulesWithFuel = (modules: Array[Int]) => {
    var sum: Int = 0
    for (mass <- modules) {
      sum += forMassWithFuel(mass)
    }
    sum
  }
  
}