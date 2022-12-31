package net.st915.typesafescalajs.elements.attributes

import net.st915.typesafescalajs.elements.properties.Children

trait HasChildren[A] {

  def children: Children
  
  def copyWith(newProperty: Children): A
  
}
