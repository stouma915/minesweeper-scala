package net.st915.typesafescalajs.elements.copyables

import net.st915.typesafescalajs.elements.properties.RightClickEvent

trait CanCopyWithNewRightClickEvent[A] {

  def copyWith(newProperty: RightClickEvent): A
  
}
