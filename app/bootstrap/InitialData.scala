package bootstrap

import com.google.inject.Inject

//import repo.AliasRepository
//import models.Alias
//
//import play.api.libs.concurrent.Execution.Implicits.defaultContext
//import play.Logger
//
//import scala.concurrent.Await
//import scala.concurrent.duration.Duration
//
//class InitialData @Inject() (aliasRepo: AliasRepository) {
//
//  def insert = for {
//    aliases <- aliasRepo.getAll() if (aliases.length == 0)
//    _ <- aliasRepo.insertAll(Data.aliases)
//  } yield {}
//
//  try {
//    Logger.info("DB initialization.................")
//    Await.result(insert, Duration.Inf)
//  }
//  catch {
//    case ex: Exception =>
//      Logger.error("Error in database initialization ", ex)
//  }
//}
//
//object Data {
//  val aliases = List()
//}
