package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.HTMLElement

object CanAppendElement {

  def apply[F[_], A](using ev: CanAppendElement[F, A]): CanAppendElement[F, A] = ev

}

trait CanAppendElement[F[_], A] {

  def apply(child: HTMLElement)(parent: A): F[A]

  def apply(childs: List[HTMLElement])(parent: A): F[A]

  def apply(childs: HTMLElement*)(parent: A): F[A]

}
