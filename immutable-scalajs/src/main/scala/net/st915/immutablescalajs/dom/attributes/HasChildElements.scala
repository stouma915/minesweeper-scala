package net.st915.immutablescalajs.dom.attributes

import net.st915.immutablescalajs.dom.HTMLElement

trait HasChildElements[A] {

  def childElements: List[HTMLElement]

  def copyWithNewChildElements(newChildElements: List[HTMLElement]): A

}
