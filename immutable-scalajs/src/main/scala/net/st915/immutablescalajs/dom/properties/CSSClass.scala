package net.st915.immutablescalajs.dom.properties

case class CSSClass(raw: String*) {

  def unwrap: String = raw.mkString(" ")

}
