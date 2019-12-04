package io.github.zebalu.advent2019

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PasswordSuite extends FunSuite {
 
  test("example 0 is valid") {
    assert(new Password(111111).isValid)
  }
  
  test("example 1 is valid") {
    assert(new Password(122345).isValid)
  }
  
  test("example 2 is not valid") {
    assert(!new Password(135679).isValid)
  }
  
  test("example 3 is not valid") {
    assert(!new Password(223450).isValid)
  }
  
  test("example 4 is not valid") {
    assert(!new Password(123789).isValid)
  }
  
  test("extra rule example 1 is valid") {
    assert(new Password(112233).isExtendedValid)
  }
  
  test("extra rule example 2 is invalid") {
    assert(!new Password(123444).isExtendedValid)
  }
  
  test("extra rule example 3 is valid") {
    assert(new Password(111133).isExtendedValid)
  }
  
  test("extra rule rejects 266666") {
    assert(!new Password(266666).isExtendedValid)
  }
  
}