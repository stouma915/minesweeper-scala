package net.st915.typesafescalajs.translater.instances

import cats.effect.unsafe.IORuntime
import cats.effect.{IO, Sync}
import cats.{Eq, Monoid}
import net.st915.typesafescalajs.*
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.attributes.*
import net.st915.typesafescalajs.elements.properties.*
import net.st915.typesafescalajs.nodes.*
import net.st915.typesafescalajs.translater.CanTranslate

import scala.util.chaining.*

class SyncCanTranslate[F[_]: Sync] extends CanTranslate[F] {

  import cats.syntax.flatMap.*
  import cats.syntax.functor.*
  import cats.syntax.traverse.*

  private def createElement[A](tagName: String)(using ScalaJSDocument): F[A] =
    Sync[F].pure(summon[ScalaJSDocument].createElement(tagName).asInstanceOf[A])

  private def createTextNode(original: TextNode)(using ScalaJSDocument): F[ScalaJSTextNode] =
    Sync[F].pure(summon[ScalaJSDocument].createTextNode(original.innerText.raw))

  private def applyClassName[A <: ScalaJSElement](original: HasClassName)(sjsElem: A): F[A] =
    Sync[F].blocking {
      if (Eq[ClassName].eqv(original.className, Monoid[ClassName].empty)) {
        sjsElem
      } else {
        sjsElem.tap(_.className = original.className.raw)
      }
    }

  private def applyID[A <: ScalaJSElement](original: HasID)(sjsElem: A): F[A] =
    Sync[F].blocking {
      if (Eq[ID].eqv(original.id, Monoid[ID].empty)) {
        sjsElem
      } else {
        sjsElem.tap(_.id = original.id.raw)
      }
    }

  private def applyClickEvent[A <: ScalaJSElement](original: Clickable)(sjsElem: A)(using
  IORuntime): F[A] =
    Sync[F].blocking {
      if (Eq[ClickEvent].eqv(original.clickEvent, Monoid[ClickEvent].empty)) {
        sjsElem
      } else {
        sjsElem.tap {
          _.onclick = e => {
            e.preventDefault()
            original.clickEvent.raw.unsafeRunAndForget()
          }
        }
      }
    }

  private def applyRightClickEvent[A <: ScalaJSElement](original: RightClickable)(sjsElem: A)(using
  IORuntime): F[A] =
    Sync[F].blocking {
      if (Eq[RightClickEvent].eqv(original.rightClickEvent, Monoid[RightClickEvent].empty)) {
        sjsElem
      } else {
        sjsElem.tap {
          _.oncontextmenu = e => {
            e.preventDefault()
            original.rightClickEvent.raw.unsafeRunAndForget()
          }
        }
      }
    }

  private def applyHyperlink[A <: ScalaJSAnchor](original: HasHyperlink)(sjsElem: A): F[A] =
    Sync[F].blocking {
      if (Eq[Link].eqv(original.href, Monoid[Link].empty)) {
        sjsElem
      } else {
        sjsElem.tap(_.href = original.href.raw)
      }
    }

  private def applyChilds[A <: ScalaJSElement](original: HasChilds)(sjsElem: A)(
    using ScalaJSDocument,
    IORuntime
  ): F[A] =
    for {
      childs <- original.childs.raw.map(apply).sequence
      _ <- childs.map { child => Sync[F].blocking(sjsElem.appendChild(child)) }.sequence
    } yield sjsElem

  private def applyCommonProperties[A <: ScalaJSElement](original: Element)(sjsElem: A)(
    using ScalaJSDocument,
    IORuntime
  ): F[A] =
    applyClassName(original)(sjsElem) >>=
      applyID(original) >>=
      applyClickEvent(original) >>=
      applyRightClickEvent(original) >>=
      applyChilds(original)

  private def asSJSNode[A <: ScalaJSNode](original: A): F[ScalaJSNode] =
    Sync[F].blocking(original.asInstanceOf[ScalaJSNode])

  override def apply(node: Node)(using ScalaJSDocument, IORuntime): F[ScalaJSNode] =
    node match
      case a: TextNode =>
        createTextNode(a) >>=
          asSJSNode
      case a: Anchor =>
        createElement[ScalaJSAnchor]("a") >>=
          applyCommonProperties(a) >>=
          applyHyperlink(a) >>=
          asSJSNode
      case a: BR =>
        createElement[ScalaJSBR]("br") >>=
          applyCommonProperties(a) >>=
          asSJSNode
      case a: Div =>
        createElement[ScalaJSDiv]("div") >>=
          applyCommonProperties(a) >>=
          asSJSNode
      case a: H1 =>
        createElement[ScalaJSH1]("h1") >>=
          applyCommonProperties(a) >>=
          asSJSNode
      case a: Paragraph =>
        createElement[ScalaJSParagraph]("p") >>=
          applyCommonProperties(a) >>=
          asSJSNode
      case a: Span =>
        createElement[ScalaJSSpan]("span") >>=
          applyCommonProperties(a) >>=
          asSJSNode

}
