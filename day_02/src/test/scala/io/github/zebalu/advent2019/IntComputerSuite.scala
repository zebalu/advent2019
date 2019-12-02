package io.github.zebalu.advent2019

import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class IntComputerSuite extends FunSuite {
  test("example 1") {
    val array = Array(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)
    new IntComputer(array).execute
    assert(3500 == array(0))
  }

  test("example 2") {
    val array = Array(1, 0, 0, 0, 99)
    new IntComputer(array).execute
    assert(2 == array(0))
  }

  test("example 3") {
    val array = Array(2, 3, 0, 3, 99)
    new IntComputer(array).execute
    assert(6 == array(3))
  }

  test("example 4") {
    val array = Array(2, 4, 4, 5, 99, 0)
    new IntComputer(array).execute
    assert(9801 == array(5))
  }

  test("example 5") {
    val array = Array(1, 1, 1, 4, 99, 5, 6, 0, 99)
    new IntComputer(array).execute
    assert(30 == array(0))
  }

  test("input") {
    val array = Array(1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 6, 19, 1, 19, 5, 23, 2, 13, 23, 27, 1, 10, 27, 31, 2, 6, 31, 35, 1, 9, 35, 39, 2, 10, 39, 43, 1, 43, 9, 47, 1, 47, 9, 51, 2, 10, 51, 55, 1, 55, 9, 59, 1, 59, 5, 63, 1, 63, 6, 67, 2, 6, 67, 71, 2, 10, 71, 75, 1, 75, 5, 79, 1, 9, 79, 83, 2, 83, 10, 87, 1, 87, 6, 91, 1, 13, 91, 95, 2, 10, 95, 99, 1, 99, 6, 103, 2, 13, 103, 107, 1, 107, 2, 111, 1, 111, 9, 0, 99, 2, 14, 0, 0)
    new IntComputer(array).execute
    println(array(0))
    
  }
  
  test("restoree 1202") {
    val array = Array(1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 1, 6, 19, 1, 19, 5, 23, 2, 13, 23, 27, 1, 10, 27, 31, 2, 6, 31, 35, 1, 9, 35, 39, 2, 10, 39, 43, 1, 43, 9, 47, 1, 47, 9, 51, 2, 10, 51, 55, 1, 55, 9, 59, 1, 59, 5, 63, 1, 63, 6, 67, 2, 6, 67, 71, 2, 10, 71, 75, 1, 75, 5, 79, 1, 9, 79, 83, 2, 83, 10, 87, 1, 87, 6, 91, 1, 13, 91, 95, 2, 10, 95, 99, 1, 99, 6, 103, 2, 13, 103, 107, 1, 107, 2, 111, 1, 111, 9, 0, 99, 2, 14, 0, 0)
    array(1)=12
    array(2)=2
    new IntComputer(array).execute
    println(array(0))
    
  }

}