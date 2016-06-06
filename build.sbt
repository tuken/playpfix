import com.typesafe.config.ConfigFactory

name := """playpfix"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.1.1",
  "com.h2database" % "h2" % "1.4.187" ,
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.2.0",
  "joda-time" % "joda-time" % "2.9.4",
  "org.joda" % "joda-convert" % "1.7",
  "mysql" % "mysql-connector-java" % "5.1.36",
  specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Slick code generator
slickCodeGen <<= slickCodeGenTask // register sbt command
//(compile in Compile) <<= (compile in Compile) dependsOn (slickCodeGenTask) // register automatic code generation on compile
lazy val config = ConfigFactory.parseFile(new File("./conf/application.conf"))
lazy val slickCodeGen = taskKey[Seq[File]]("slick-codegen")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val slickDriver = config.getString("slick.dbs.default.driver").init
  val jdbcDriver = config.getString("slick.dbs.default.db.driver")
  val url = config.getString("slick.dbs.default.db.url")
  val outputDir = "app/"
  val pkg = "models"
  val username = config.getString("slick.dbs.default.db.user")
  val password = config.getString("slick.dbs.default.db.password")

  toError(
    r.run(
      "slick.codegen.SourceCodeGenerator",
      cp.files,
      Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password),
//      Array("slick.driver.MySQLDriver$".init, "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/pfix", outputDir, pkg, username, password),
      s.log
    )
  )
  val fname = outputDir + "/Tables.scala"
  Seq(file(fname))
}
