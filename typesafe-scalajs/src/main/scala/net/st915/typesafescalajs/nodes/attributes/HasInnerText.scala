package net.st915.typesafescalajs.nodes.attributes

import net.st915.typesafescalajs.nodes.properties.InnerText

trait HasInnerText[A] {

  def innerText: InnerText

  def copyWith(newProperty: InnerText): A

}
