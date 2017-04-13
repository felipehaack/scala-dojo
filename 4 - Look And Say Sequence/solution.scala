import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

/*
  Seqüência Look-and-Say
  Este problema foi utilizado em 144 Dojo(s).
  A seqüência de números inteiros obtido a partir de um dígito (qualquer valor entre 1 e 9) onde o termo seguinte é obtido pela descrição do termo anterior é definida como uma seqüência look-and-say.
  Por exemplo, tendo como dígito inicial 1:
  1 é descrito como "um 1" ou 11;
  11 é descrito como "dois 1" ou 21;
  21 é descrito como "um 2, um 1" ou 1211;
  1211 é descrito como "um 1, um 2, dois 1" ou 111221;
  111221 é descrito como "três 1, dois 2, um 1" ou 312211.
  Para dígitos maiores ou iguais a 2, a seqüência é tem o seguinte formato: d, 1d, 111d, 311d, 13211d, 111312211d (sendo d o dígito inicial).
  Dado o dígito inicial da seqüência, determine a soma de todos os dígitos do 50º elemento da seqüência.
*/

//Willian
/*def lookAndSay(sequence: String) = {

  var last = sequence.head
  var count = 1
  val res = ListBuffer[(Char, Int)]()

  for {
    c <- sequence.tail
  } {
    if (last == c) {
      count += 1

    } else {
      res += ((last, count))
      count = 1
      last = c
    }
  }
  res += ((last, count))

  res.map(t => s"${t._2}${t._1}").mkString
}

println(lookAndSay("11"))*/

//Willian
/*def lookAndSayII(sequence: String): String = {
  @tailrec
  def lookAndSayII(last: (Char, Int), tail: List[Char], res: List[(Char, Int)]): String = tail match {
    case Nil => (last +: res).map(t => s"${t._2}${t._1}").mkString
    case h :: t if h == last._1 => lookAndSayII((h, last._2 + 1), t, res)
    case h :: t => lookAndSayII((h, 1), t, last +: res)
  }

  lookAndSayII((sequence.head, 1), sequence.toList.tail, List())
}

println(lookAndSayII("12"))*/

// Luckcheese
/*def lookAndSayIII(sequence: String): String = {
  //@tailrec
  def lookAndSayIII(last: (Char, Int), tail: List[Char]): List[(Char, Int)] = tail match {
    case Nil => List(last)
    case h :: t if h == last._1 => lookAndSayIII((h, last._2 + 1), t)
    case h :: t => last +: lookAndSayIII((h, 1), t)
  }

  lookAndSayIII((sequence.head, 1), sequence.toList.tail).map(t => s"${t._2}${t._1}").mkString
}

println(lookAndSayIII("12"))*/

//Felipe
/*def lookAndSayRecFIV(sequence: String): String = {

  //@tailrec
  def lookAndSayRecFIV(head: String, tail: String, count: Int): String = tail.length match {
    case 0 => count.toString + head
    case 2 => head(0).toString + lookAndSayRecFIV(head(1).toString, tail, count + 1)
    case _ =>
      if (head == tail.head.toString)
        head + lookAndSayRecFIV(tail.head.toString, tail.tail, count + 1)
      else
        lookAndSayRecFIV(count.toString + head, tail.tail, 1)
  }

  lookAndSayRecFIV(sequence.head.toString, sequence.tail, 1)
}*/

//Felipe
/*
def lookAndSayRecFV(sequence: String): String = {

  @tailrec
  def lookAndSayRecFV(h: String, t: String, count: Int): String = t.length match {
    case 0 => h + count.toString
    case _ if h != t.head.toString =>
      h + count.toString + lookAndSayRecFV(t.head.toString, t.tail, 1)
    case _ if h == t.head.toString =>
      h + lookAndSayRecFV(t.head.toString, t.tail, count + 1)
  }

  lookAndSayRecFV(sequence.head.toString, sequence.tail, 1)
}

println(lookAndSayRecFV("22333444"))*/
