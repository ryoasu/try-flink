name := "try-flink"

version := "1.0"

scalaVersion := "2.11.7"

val flinkVersion = "1.3.1"

libraryDependencies ++=
  Seq(
  "org.apache.flink" %% "flink-scala"           % flinkVersion % "provided",
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion % "provided",
  "org.scalatest"    %% "scalatest"             % "2.2.4"      % "test",
  "org.scalacheck"   %% "scalacheck"            % "1.12.2"     % "test",
  "org.slf4j"         % "slf4j-api"             % "1.7.10"
  )

fork in run := true