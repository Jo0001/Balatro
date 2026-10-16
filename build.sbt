scalaVersion := "3.9.0"

lazy val root = rootProject
  .settings(
    name := "Balatro",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.14",
      "org.scalatest" %% "scalatest" % "3.2.14" % "test"
    )
  )
