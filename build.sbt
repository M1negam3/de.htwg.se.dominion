name          := "de.htwg.se.dominion"
version       := "0.1"
scalaVersion  := "2.12.8"

libraryDependencies ++= {
  Seq(
    "org.scalactic" %% "scalactic" % "3.0.5",
    "org.scalatest" %% "scalatest" % "3.0.5" % "test",

    "org.scala-lang.modules" %% "scala-swing" % "2.1.1"
  )
}