package net.st915.typesafescalajs.elements

import net.st915.typesafescalajs.Node
import net.st915.typesafescalajs.elements.attributes.*

trait Element[A] extends Node
    with HasClassName[A]
    with HasID[A]
    with HasChilds[A]
    with Clickable[A]
    with RightClickable[A]
