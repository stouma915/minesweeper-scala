package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.HTMLElement

object CanAppendChild {

  def apply[F[_], A](using ev: CanAppendChild[F, A]): CanAppendChild[F, A] = ev

}

trait CanAppendChild[F[_], A] {

  def apply[B <: HTMLElement](child: B)(parent: A): F[A]

  def apply[B <: HTMLElement](fchild: F[B])(parent: A): F[A]

}
