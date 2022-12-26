package net.st915.typesafescalajs.typeclasses

object CanCopyWithNewProperty {

  def apply[A, B](using ev: CanCopyWithNewProperty[A, B]): CanCopyWithNewProperty[A, B] = ev

}

trait CanCopyWithNewProperty[A, B] {

  def apply(newProperty: B)(element: A): A

}
