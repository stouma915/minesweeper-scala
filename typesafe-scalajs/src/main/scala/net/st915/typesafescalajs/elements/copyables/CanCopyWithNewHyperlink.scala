package net.st915.typesafescalajs.elements.copyables

import net.st915.typesafescalajs.elements.properties.Link

trait CanCopyWithNewHyperlink[A] {

  def copyWith(newProperty: Link): A

}
