package net.paulsasi.bs.persistence.api

import net.paulsasi.bs.entities.Author

class AuthorPersistenceException(s: String) extends Exception(s){}

trait AuthorRepository {
  
  def getAllAuthors(): Option[List[Author]]
  
  def getAuthor(id: Long): Option[Author]

  @throws(classOf[AuthorPersistenceException])
  def insertAuthor(author: Author): Unit

  @throws(classOf[AuthorPersistenceException])
  def deleteAuthor(id: Long): Unit

  @throws(classOf[AuthorPersistenceException])
  def updateAuthor(author: Author): Unit
}
