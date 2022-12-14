package net.st915.immutablescalajs.dom.properties

case class CSSClass(raw: String*) {

  def parse: String = raw.mkString(" ")

}
