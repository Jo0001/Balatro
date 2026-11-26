package de.htwg.se.balatro.util

enum Result[+A]:
  case Ok(value: A)
  case Err(message: String)

  def map[B](f: A => B): Result[B] = this match
    case Ok(a) => Ok(f(a))
    case Err(m) => Err(m)

  def flatMap[B](f: A => Result[B]): Result[B] = this match
    case Ok(a) => f(a)
    case Err(m) => Err(m)

