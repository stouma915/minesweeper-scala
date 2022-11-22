package net.st915.minesweeper

import cats.effect.IO
import org.scalajs.dom.document

@main def main(): Unit = {
  
  import cats.effect.unsafe.implicits.global

  val printTestMessage = IO {
    println("TEST")
  }

  val addTestElement = IO {
    val testElement = document.createElement("h1")
    testElement.textContent = "TEST"
    document.body.appendChild(testElement)
  }

  val task = for {
    _ <- printTestMessage
    _ <- addTestElement
  } yield ()

  task.unsafeRunAndForget()

}
