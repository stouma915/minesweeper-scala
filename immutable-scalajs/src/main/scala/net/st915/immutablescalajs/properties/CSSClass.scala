package net.st915.immutablescalajs.properties

case class CSSClass(raw: String*) {

  def parse: String = raw.mkString(" ")

}
