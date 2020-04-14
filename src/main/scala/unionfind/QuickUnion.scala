package unionfind

import scala.annotation.tailrec

class QuickUnion(n: Int) extends UnionFind {
  private val id = (0 until n).toArray

  /** change root of p to point to root of q (depth of p and q array accesses) */
  override def union(p: Int, q: Int): Unit = id(root(p)) = root(q)

  /** check if p and q have same root (depth of p and q array accesses) */
  override def connected(p: Int, q: Int): Boolean = root(p) == root(q)

  /** chase parent pointers until reach root (depth of i array accesses) */
  @tailrec
  private def root(i: Int): Int =
    if (i == id(i)) i else root(id(i))
}