addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.3")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.1"

addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.3")

// For ScalaPB 0.11.x:
libraryDependencies ++= Seq(
  "com.thesamet.scalapb" %% "compilerplugin"           % "0.11.1",
  "com.thesamet.scalapb" %% "scalapb-validate-codegen" % "0.3.1"
)