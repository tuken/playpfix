package repo

import javax.inject.{Inject, Singleton}

import models.Alias
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future
import java.util.Date

import org.joda.time.DateTime

//@Singleton()
//class AliasRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends AliasTable with HasDatabaseConfigProvider[JdbcProfile] {
//
//  import driver.api._
//
//  def insert(alias: Alias): Future[Int] = db.run { aliasesQueryInc += alias }
//
//  def insertAll(aliases: List[Alias]): Future[Seq[Int]] = db.run { aliasesQueryInc ++= aliases }
//
//  def update(alias: Alias): Future[Int] = db.run { aliasesQuery.filter(_.id === alias.id).update(alias) }
//
//  def delete(id: Int): Future[Int] = db.run { aliasesQuery.filter(_.id === id).delete }
//
//  def getAll(): Future[List[Alias]] = db.run { aliasesQuery.to[List].result }
//
//  def getById(aliasId: Int): Future[Option[Alias]] = db.run { aliasesQuery.filter(_.id === aliasId).result.headOption }
//
//  def ddl = aliasesQuery.schema
//}
//
//private[repo] trait AliasTable { self: HasDatabaseConfigProvider[JdbcProfile] =>
//
//  import driver.api._
//
//  private[AliasTable] class AliasTable(tag: Tag) extends Table[Alias](tag, "alias") {
//    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
//    val domain_id: Rep[Int] = column[Int]("domain_id")
//    val address: Rep[String] = column[String]("address", O.Length(255, varying = true), O.Default(""))
//    val goto: Rep[String] = column[String]("goto", O.SqlType("TEXT"))
//    val created_at: Rep[DateTime] = column[DateTime]("created_at")
//    val updated_at: Rep[DateTime] = column[DateTime]("updated_at")
//    val active: Rep[Boolean] = column[Boolean]("active", O.Default(true))
////    def emailUnique = index("email_unique_key", email, unique = true)
//    def * = (id, domain_id, address, goto, created_at, updated_at, active) <> (Alias.tupled, Alias.unapply)
//  }
//
//  lazy protected val aliasesQuery = TableQuery[Alias]
//
//  lazy protected val aliasesQueryInc = aliasesQuery returning aliasesQuery.map(_.id)
//}
