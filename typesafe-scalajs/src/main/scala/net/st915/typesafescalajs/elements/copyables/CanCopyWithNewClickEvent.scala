package net.st915.typesafescalajs.elements.copyables

import net.st915.typesafescalajs.elements.properties.ClickEvent

trait CanCopyWithNewClickEvent[A] {

  def copyWith(newProperty: ClickEvent): A

}
