def mijao(ocup: List[Int], mics: Int) : Int = {
  def mijao(ocup: List[Int], mics: List[Int], acc: Int) : Int = {
      mics.filterNot(i => (i-1 to i+1).exists(ocup.contains)) match {
        case Nil => acc
        case h :: t => mijao (h +: ocup, t, acc + 1)
      }
  }
  //
  mijao(ocup, (0 until mics).toList, 0)
}

mijao(List(0,4), 10)
