package io.github.zebalu.advent2019

import scala.collection.mutable.ListBuffer

class PasswordRange(from: Int, to: Int) {
  val passwords = {
    var buffer = new ListBuffer[Password]
    var pasword = new Password(from)
    while(pasword.lessOrEqual(to)) {
      if(pasword.isValid) {
        buffer+=pasword
      }
      pasword=pasword.next
    }
    buffer.toList
  }
  
  val extendedValidityPasswords = {
    var buffer = new ListBuffer[Password]
    for(p <- passwords) {
      if(p.isExtendedValid) {
        buffer += p
      }
    }
    buffer.toList
  }
  
  val size = passwords.size
  val extraRuleSize = extendedValidityPasswords.size
}