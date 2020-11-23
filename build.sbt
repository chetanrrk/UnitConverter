name := "unit-converter"
organization := "com.citrine"
maintainer := "chetanrrk@gmail.com"
version := "1.0.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.1"

libraryDependencies += guice

