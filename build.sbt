name := "cas-tools"

version := "0.1"

scalaVersion := "2.11.12"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies := Seq(
  "com.github.alexarchambault" %%% "case-app" % "2.0.0-M2",
  "biz.enef" %%% "slogging" % "0.6.1",
  "com.lihaoyi"       %%% "fastparse" % "1.0.0"
)

enablePlugins(ScalaNativePlugin)
