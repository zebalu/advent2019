package io.github.zebalu.advent2019

import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class FuelCalculatorSuite extends FunSuite {
  
  test("example 1") {
    assert(33583==FuelCalculator.forMass(100756))
  }
  
  test("all modules") {
    assert(3361299 == FuelCalculator.forModules(SpaceCraftModuleLoader.modules))
  }
  
  test("with weight of fuel") {
    assert(5039071 == FuelCalculator.forModulesWithFuel(SpaceCraftModuleLoader.modules))
  }
  
}