name := "trigit_process"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.18"
)

play.Keys.lessEntryPoints <<= baseDirectory(_ / "app" / "assets" / "stylesheets" ** "main.less")

play.Project.playJavaSettings
