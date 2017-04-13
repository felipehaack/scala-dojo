
/*
 Composable Filters

 TODO - Some("x == ? AND y == ?", 1, 2)
 */

trait Filter {
  def toSql: Option[String]
}

abstract class OperationFilter[T](op: String, column: String, variable: Option[T]) extends Filter {
  override def toSql = variable.map(v => s"$column $op $v")
}

case class GreaterThanFilter[T](column: String, value: Option[T]) extends OperationFilter(">", column, value)

case class GreaterEqualFilter[T](column: String, value: Option[T]) extends OperationFilter(">=", column, value)

case class LessThanFilter[T](column: String, value: Option[T]) extends OperationFilter("<", column, value)

case class LessEqualFilter[T](column: String, value: Option[T]) extends OperationFilter("<=", column, value)

case class EqualFilter[T](column: String, value: Option[T]) extends OperationFilter("==", column, value)

case class RangeFilter[T](column: String, from: Option[T], to: Option[T] = None) extends Filter {

  override def toSql = And(
    GreaterEqualFilter(column, from),
    LessEqualFilter(column, to)
  ).toSql
}

abstract class JoinFilter(op: String, filters: Filter*) extends Filter {
  override def toSql = {
    val validationFilters = filters
      .map(_.toSql)
      .filter(_.isDefined)
      .map(_.get)

    validationFilters.size match {
      case 0 => None
      case 1 => Some(validationFilters.head)
      case _ => Some(validationFilters.mkString("(", s" $op ", ")"))
    }
  }
}

case class And(fs: Filter*) extends JoinFilter("AND", fs: _*)

case class Or(fs: Filter*) extends JoinFilter("OR", fs: _*)

case class InFilter[T](column: String, in: T*) extends Filter {
  override def toSql = in.toList match {
    case Nil => None
    case in => Some(in.mkString(s"$column IN (", ", ", ")"))
  }
}

implicit def elementToOption[T](value: T): Some[T] = Some(value)

val q = Or(
  RangeFilter("amount", 1),
  EqualFilter("id", 10),
  And(
    EqualFilter("hello", "world"),
    EqualFilter("x", 10)
  ),
  InFilter("dark side", 1, 2, 3)
)

q.toSql.foreach(println)