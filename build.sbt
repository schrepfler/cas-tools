import sbt._

import scala.collection.immutable.Seq

name := "cas-tools"

version := "0.1"

scalaVersion := "2.11.12"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies := Seq(
  "com.github.alexarchambault" %%% "case-app" % "2.0.0-M3",
  "biz.enef" %%% "slogging" % "0.6.1",
  "com.lihaoyi" %%% "fastparse" % "1.0.0",
  "com.lihaoyi" %%% "utest" % "0.5.3" % "test"
)

nativeMode := Properties.nativeMode

nativeGC := "none"

nativeLinkingOptions := {
  val dynamicLinkerOptions =
    Properties.dynamicLinker.toVector
      .map(dl => s"-Wl,--dynamic-linker=$dl")

  dynamicLinkerOptions ++ Seq(
    "-lcurl"
  ) ++ sys.props
    .get("nativeLinkingOptions")
    .fold(Seq.empty[String])(_.split(" ").toVector)
}

Keys.`package` in Compile := {
  val casToolsOut = (nativeLink in Compile).value
  val outputDirectory = target.value / "output"

  IO.deleteFilesEmptyDirs(Seq(outputDirectory))
  IO.createDirectory(outputDirectory / "lib")
  IO.createDirectory(outputDirectory / "bin")

  IO.copyFile(casToolsOut, outputDirectory / "bin" / "cas-tools")
  AdditionalIO.setExecutable(outputDirectory / "bin" / "cas-tools")

  (outputDirectory / "cas-tools").setExecutable(true)

  streams.value.log.info(s"Created files in $outputDirectory")

  outputDirectory
}

enablePlugins(ScalaNativePlugin)
