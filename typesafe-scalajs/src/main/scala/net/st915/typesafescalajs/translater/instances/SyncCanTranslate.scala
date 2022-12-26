package net.st915.typesafescalajs.translater.instances

import cats.{Eq, Monoid}
import cats.effect.Sync
import net.st915.typesafescalajs.*
import net.st915.typesafescalajs.elements.*
import net.st915.typesafescalajs.elements.attributes.HasHyperlink
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

  private def applyClassName[A <: ScalaJSElement](original: Element)(sjsElem: A): F[A] =
    Sync[F].blocking {
      if (Eq[ClassName].eqv(original.className, Monoid[ClassName].empty)) {
        sjsElem
      } else {
        sjsElem.tap(_.className = original.className.raw)
      }
    }

  private def applyID[A <: ScalaJSElement](original: Element)(sjsElem: A): F[A] =
    Sync[F].blocking {
      if (Eq[ID].eqv(original.id, Monoid[ID].empty)) {
        sjsElem
      } else {
        sjsElem.tap(_.id = original.id.raw)
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

  private def applyChilds[A <: ScalaJSElement](original: Element)(sjsElem: A)(using
  ScalaJSDocument): F[A] =
    for {
      childs <- original.childs.raw.map(apply).sequence
      _ <- childs.map { child => Sync[F].blocking(sjsElem.appendChild(child)) }.sequence
    } yield sjsElem

  private def applyClassIDChilds[A <: ScalaJSElement](original: Element)(sjsElem: A)(using
  ScalaJSDocument): F[A] =
    applyClassName(original)(sjsElem) >>= applyID(original) >>= applyChilds(original)

  private def asSJSNode[A <: ScalaJSNode](original: A): F[ScalaJSNode] =
    Sync[F].blocking(original.asInstanceOf[ScalaJSNode])

  override def apply(node: Node)(using ScalaJSDocument): F[ScalaJSNode] =
    node match
      case a: TextNode =>
        createTextNode(a) >>=
          asSJSNode
      case a: Anchor =>
        createElement[ScalaJSAnchor]("a") >>=
          applyClassIDChilds(a) >>=
          applyHyperlink(a) >>=
          asSJSNode
      case a: BR =>
        createElement[ScalaJSBR]("br") >>=
          applyClassIDChilds(a) >>=
          asSJSNode
      case a: Div =>
        createElement[ScalaJSDiv]("div") >>=
          applyClassIDChilds(a) >>=
          asSJSNode
      case a: H1 =>
        createElement[ScalaJSH1]("h1") >>=
          applyClassIDChilds(a) >>=
          asSJSNode
      case a: Paragraph =>
        createElement[ScalaJSParagraph]("p") >>=
          applyClassIDChilds(a) >>=
          asSJSNode
      case a: Span =>
        createElement[ScalaJSSpan]("span") >>=
          applyClassIDChilds(a) >>=
          asSJSNode

}
