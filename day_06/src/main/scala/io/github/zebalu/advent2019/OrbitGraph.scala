package io.github.zebalu.advent2019

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.immutable.Set

class OrbitGraph {

  private val com: OrbitingObject = OrbitingObjectCreator.create("COM", null)
  private var couldNotAdd: List[(String, String)] = List.empty

  def append(base: String, obj: String): Unit = {
    if (com.isObject(base)) {
      com.addObject(OrbitingObjectCreator.create(obj, com))
    } else if (com.hasObject(base)) {
      com.appendObject(base, obj)
      while (!couldNotAdd.isEmpty && addIfPossible(List.empty, couldNotAdd.head, couldNotAdd.tail)) {}
    } else {
      couldNotAdd = couldNotAdd :+ (base, obj)
    }
  }

  def getOrbitCount(): Int = {
    var sum = 0
    for (o <- OrbitingObjectCreator.objects) {
      sum += o.orbitCount
    }
    sum
  }

  def addIfPossible(start: List[(String, String)], element: (String, String), rest: List[(String, String)]): Boolean = {
    if (com.hasObject(element._1)) {
      com.appendObject(element._1, element._2)
      couldNotAdd = start ++ rest
      if (!rest.isEmpty) {
        addIfPossible(start, rest.head, rest.tail)
      }
      true
    } else if (!rest.isEmpty) {
      addIfPossible(start :+ element, rest.head, rest.tail)
    } else {
      false
    }
  }

  def minRoute(from: String, to: String): Int = {
    val fromPath = OrbitingObjectCreator.getObjectPathToCom(from)
    val toPath = OrbitingObjectCreator.getObjectPathToCom(to)
    var fromOO = OrbitingObjectCreator.getObject(from)
    var toOO = OrbitingObjectCreator.getObject(to)
    var fromCount = 0
    while (fromOO != null && !toPath.contains(fromOO.getLabel)) {
      fromCount += 1
      fromOO = fromOO.getParent
    }
    var toCount = 0
    while (toOO != null && !fromPath.contains(toOO.getLabel)) {
      toCount += 1
      toOO = toOO.getParent
    }
    fromCount + toCount - 2
  }

  private class OrbitingObject(label: String, parent: OrbitingObject) {

    val getLabel = label
    val getParent = parent
    private var orbitingObjects: List[OrbitingObject] = List.empty
    def addObject(obj: OrbitingObject): Unit = orbitingObjects = orbitingObjects :+ obj
    val isObject = (oLabel: String) => label.equals(oLabel)
    val orbitCount: Int = parent match {
      case null => 0
      case _ => 1 + parent.orbitCount
    }
    def hasObject(oLabel: String): Boolean = {
      findObject(oLabel) != null
    }
    private def findObject(oLabel: String): OrbitingObject = {
      val found = OrbitingObjectCreator.nameToOrbitingMap.get(oLabel)
      if (found.isDefined) {
        found.get
      } else {
        null
      }
    }
    def appendObject(oLabel: String, obj: String): Unit = {
      val parent = findObject(oLabel)
      if (parent != null) {
        parent.addObject(OrbitingObjectCreator.create(obj, parent))
      } else {
        throw new IllegalArgumentException("can not extend " + oLabel + "since it is not here")
      }
    }
  }

  private object OrbitingObjectCreator {

    var objects: ArrayBuffer[OrbitingObject] = new ArrayBuffer[OrbitingObject]
    var childToParentMap: HashMap[String, String] = HashMap.empty
    var nameToOrbitingMap: HashMap[String, OrbitingObject] = HashMap.empty

    def create(name: String, parent: OrbitingObject): OrbitingObject = {
      val o = new OrbitingObject(name, parent)
      objects += o
      if (parent != null) {
        val pl = parent.getLabel
        childToParentMap += (name -> pl)
      }
      nameToOrbitingMap += (name -> o)
      o
    }

    def getObject(name: String): OrbitingObject = {
      nameToOrbitingMap.get(name).get
    }

    def getObjectPathToCom(name: String): Set[String] = {
      var result: ArrayBuffer[String] = ArrayBuffer.empty
      var oo = nameToOrbitingMap.get(name).get
      while (oo != null) {
        result += oo.getLabel
        oo = oo.getParent
      }
      return result.toSet
    }
  }
}