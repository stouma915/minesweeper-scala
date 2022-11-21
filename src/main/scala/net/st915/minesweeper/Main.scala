package net.st915.minesweeper

import cats.effect.IO

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  val printTestMessage = IO {
    println("TEST")
  }

  val task = for {
    _ <- printTestMessage
  } yield ()

  task.unsafeRunAndForget()

}
