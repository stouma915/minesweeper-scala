package net.st915.immutablescalajs.dom.attributes

import net.st915.immutablescalajs.dom.properties.CSSClass

trait HasCSSClass[A] {

  def cssClass: Option[CSSClass]

  def copyWithNewCSSClass(newCSSClass: Option[CSSClass]): A

}
