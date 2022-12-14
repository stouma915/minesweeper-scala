package net.st915.immutablescalajs.typeclasses

import net.st915.immutablescalajs.dom.HTMLElement

object CanAddChild {

  def apply[F[_], A](using ev: CanAddChild[F, A]): CanAddChild[F, A] = ev

}

trait CanAddChild[F[_], A] {

  def apply(child: HTMLElement)(parent: A): F[A]

}
