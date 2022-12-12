package net.st915.minesweeper.eventloop.tasks.instances

import cats.effect.Async
import net.st915.minesweeper.eventloop.tasks.typeclasses.CanSleep
import org.scalajs.dom.*

import scala.concurrent.Promise
import scala.concurrent.duration.FiniteDuration
import scala.util.chaining.*

class AsyncCanSleep[F[_]: Async] extends CanSleep[F] {

  override def perform(duration: FiniteDuration)(using Window): F[Unit] =
    Async[F].fromFuture {
      Async[F].pure {
        Promise[Unit]()
          .tap { p =>
            summon[Window].setTimeout(
              () => p.success(()),
              duration.toMillis.toDouble
            )
          }
          .pipe(_.future)
      }
    }

}
