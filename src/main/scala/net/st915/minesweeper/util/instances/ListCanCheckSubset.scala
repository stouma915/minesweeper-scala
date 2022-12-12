package net.st915.minesweeper.util.instances

import net.st915.minesweeper.util.typeclasses.CanCheckSubset

object ListCanCheckSubset extends CanCheckSubset[List] {

  override def isSubset[A](a: List[A], b: List[A]): Boolean =
    a.exists { x => b.contains(x) }

}
