package unionfind

trait UnionFind {
  def union(p: Int, q: Int)
  def connected(p: Int, q: Int): Boolean
}