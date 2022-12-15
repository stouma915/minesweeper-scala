package net.st915.immutablescalajs.dom.attributes

import net.st915.immutablescalajs.dom.properties.Hyperlink

trait HasHyperlink[A] {

  def hyperlink: Option[Hyperlink]

  def copyWithNewHyperlink(newHyperlink: Option[Hyperlink]): A

}
