package net.st915.immutablescalajs.dom.attributes

import net.st915.immutablescalajs.dom.properties.CSSClass

trait HasCSSClass {

  def cssClass: Option[CSSClass]

}
