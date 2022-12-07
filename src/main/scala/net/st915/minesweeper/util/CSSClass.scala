package net.st915.minesweeper.util

case class CSSClass(classes: String*) {

  def asStr: String = classes.mkString(" ")

}
