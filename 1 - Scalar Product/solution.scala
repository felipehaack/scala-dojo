import scala.annotation.tailrec

// Haack bobão
def scalar(a: List[Int], b: List[Int]): Long = {

  var c = 0
  for (i <- a.indices) {
    c += a(i) * b(i)
  }
  c
}

val a = List(1, 1, 1)
println(s"Rafa's mimimi: ${scalar(a, a)}")

// Rafinha feliz
@tailrec
def scalarRec(a: List[Int], b: List[Int], acc: Long = 0): Option[Long] = {
  (a, b) match {
    case (Nil, Nil) => Some(acc)
    case (ha::ta, hb::tb) => scalarRec(ta, tb, ha * hb + acc)
    case _ => None
  }
}

println(s"Rafa's super mimimi: ${scalarRec(a, a)}")

def scalarZip[T:Numeric](a: List[T], b: List[T]) = {
  val n = implicitly[Numeric[T]]
  a.zip(b).map(t => n.times(t._1, t._2)).sum
}

val b = List(1.0,1.0,1.0)
println(scalarZip(a, a))
