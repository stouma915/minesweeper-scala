package net.st915.immutablescalajs.componentcreators

import net.st915.immutablescalajs.dom.HTMLElement

object CanAppendChilds {

  def apply[F[_], A](using ev: CanAppendChilds[F, A]): CanAppendChilds[F, A] = ev

}

trait CanAppendChilds[F[_], A] {

  def apply[B <: HTMLElement](childs: List[B])(parent: A): F[A]

  def apply[B <: HTMLElement](fchilds: F[List[B]])(parent: A): F[A]

}
