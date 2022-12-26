package net.st915.typesafescalajs.nodes.copyables

import net.st915.typesafescalajs.nodes.properties.InnerText

trait CanCopyWithNewInnerText[A] {

  def copyWith(newProperty: InnerText): A

}
