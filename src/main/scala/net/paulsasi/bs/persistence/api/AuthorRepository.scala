package net.paulsasi.bs.persistence.api

import net.paulsasi.bs.entities.Author

class AuthorPersistenceException(s: String) extends Exception(s){}

trait AuthorRepository {
  
  def getAllAuthors(): Option[List[Author]]
  
  def getAuthor(id: Long): Option[Author]

  @throws(classOf[AuthorPersistenceException])
  def insertAuthor(author: Author): Unit
  
  def deleteAuthor(id: Int): Unit

  @throws(classOf[AuthorPersistenceException])
  def updateAuthor(id: Int, author: Author): Unit
}
