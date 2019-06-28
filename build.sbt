name          := "de.htwg.se.dominion"
version       := "0.1"
scalaVersion  := "2.12.8"

libraryDependencies ++= {
  Seq(
    "org.scalactic" %% "scalactic" % "3.0.5",
    "org.scalatest" %% "scalatest" % "3.0.5" % "test",

    "org.scala-lang.modules" %% "scala-swing" % "2.1.1",

    "com.google.inject" % "guice" % "4.1.0",
    "net.codingwell" %% "scala-guice" % "4.1.0",

    "org.scala-lang.modules" %% "scala-xml" % "1.1.1",
    "com.typesafe.play" %% "play-json" % "2.6.6"
  )
}