package unionfind

import scala.annotation.tailrec

/**
 * Weighted quick union:
 *  - modify quick union to avoid tall trees
 *  - keep track of size of each tree (number of objects)
 *  - balance by linking root of smaller tree to root of larger tree
 */
class WeightedQuickUnion(n: Int) extends UnionFind {
  private val id = (0 until n).toArray
  private val sizes = Array.fill(n)(1)

  override def union(p: Int, q: Int): Unit = {
    val i = root(p)
    val j = root(q)
    if (i == j) return
    if (sizes(i) < sizes(j)) {
      id(i) = j
      sizes(j) += sizes(i)
    } else {
      id(j) = i
      sizes(i) += sizes(j)
    }
  }

  override def connected(p: Int, q: Int): Boolean = root(p) == root(q)

  @tailrec
  private def root(i: Int): Int =
    if (i == id(i)) i else root(id(i))
}