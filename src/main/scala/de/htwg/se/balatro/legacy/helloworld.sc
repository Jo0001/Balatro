//oneline comment
/* multi
line comment
 */

val test = 99
val a = 1
val b = 2
val c1 = a + b
val c2 = a.+(b)
print(a)
println(a % b)

val zäöüßa = 1

3.toString

val char = 'C'

val arr = Array(1, 2, 3)
arr(1)

val strArr = Array("Hi", "Hello")

val arrMixed = Array(1, "One")

val list = List(3, 2, 1)
list(2)
list.head
list.last
list.length
list.sorted
list.sortWith(_ > _)
list.filter(_ % 2 != 0)

val list2 = List(9,8,7)
list2.appendedAll(list)

val list3 = List(9,8,7)
list3.prependedAll(list)

val str = "Hello "
str.trim

val name = "John"
val strinter = s"Hallo $name"

if (str.startsWith("H"))
  print("yep")

def happy(): Boolean = true
happy()

def param(test: Any): Boolean
= true

val multi1 =
  """Line1
    Line2
    """

val multi2 =
  """Line1
    |Line2
    |""".stripMargin

param("A")
param(1)

case class Person(name: String, age: Int) {
  def hi(): String = s"I am $name and I'm $age years old"
}

class Building(name: String) {
  val some = 1
}

val build1 = Building("test")

val pers1 = Person("Jonas", 25)
pers1.name
pers1.age
pers1.hi()

def func(a: Int, b: Int, op: (Int, Int) => Int): Int
= op(a, b)

func(1, 3, (x, y) => x + y)
func(1, 3, _ + _)

val number = 5
for(i <- 1 to 5)
  println(i)