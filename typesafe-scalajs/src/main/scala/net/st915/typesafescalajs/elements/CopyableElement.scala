package net.st915.typesafescalajs.elements

import net.st915.typesafescalajs.elements.copyables.*

trait CopyableElement[A] extends Element
    with CanCopyWithNewClassName[A]
    with CanCopyWithNewID[A]
    with CanCopyWithNewChilds[A]
    with CanCopyWithNewClickEvent[A]
    with CanCopyWithNewRightClickEvent[A]
