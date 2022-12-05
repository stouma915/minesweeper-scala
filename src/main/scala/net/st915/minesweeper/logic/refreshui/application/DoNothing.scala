package net.st915.minesweeper.logic.refreshui.application

object DoNothing {

  def apply[F[_]: DoNothing]: DoNothing[F] = implicitly

}

trait DoNothing[F[_]] {

  def perform: F[Unit]

}
