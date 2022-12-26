package net.st915.typesafescalajs.elements.attributes

import net.st915.typesafescalajs.elements.properties.RightClickEvent

trait RightClickable[A] {

  def rightClickEvent: RightClickEvent

  def copyWith(newProperty: RightClickEvent): A

}
