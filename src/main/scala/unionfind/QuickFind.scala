package unionfind

class QuickFind(n: Int) extends UnionFind {
  private val id = (0 until n).toArray

  /** change all entries with id[p] to id[q] (at most 2N + 2 array accesses) */
  override def union(p: Int, q: Int): Unit = {
    val pid = id(p)
    val qid = id(q)
    for (i <- 0 until n) {
      if (id(i) == pid) {
        id(i) = qid
      }
    }
  }

  /** check whether p and q are in the same component (2 array accesses) */
  override def connected(p: Int, q: Int): Boolean = id(p) == id(q)
}