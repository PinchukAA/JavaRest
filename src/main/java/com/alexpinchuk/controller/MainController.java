package com.alexpinchuk.controller;

import com.alexpinchuk.model.Article;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;


public class MainController {
	private EntityManager em = Persistence.createEntityManagerFactory("PagePersistence").createEntityManager();
	private ObjectMapper mapper = new ObjectMapper();
	public MainController(){}

    /**
     * Return all existing article names from database.
     * @return set of string values of all existing article names
     */
	public List<Article> getArticles(){

		Query query = em.createQuery("SELECT a FROM Article a");
        List<Article> articles = new ArrayList<Article>(query.getResultList());

		return articles;
	}

    /**
     * Return article with cureent name.
     * @param id name of the article to return.
     * @return article with current name.
     */
	public Article getArticle(Long id){
        return em.find(Article.class, id);
    }

    /**
     * Delete article with current name from database
     * @param id name of the article to delete
     */
	public void deleteArticle(Long id){
        em.getTransaction().begin();
        em.remove(getArticle(id));
        em.getTransaction().commit();
	}

    /**
     * Add article to database
     * @param name name of the article
     * @param content content of the article
     * @return added article
     */
	public Article addArticle(String name, String content, Long id){
		em.getTransaction().begin();
		Article art = em.merge(new Article(name, content, id));
		em.getTransaction().commit();

		return art;
	}

    /**
     * Update article in database
     * @param name new name
     * @param content new content
     * @param id name of the existing article
     * @return updated article
     */
	public Article updateArticle(String name, String content, Long id){
		deleteArticle(id);

		em.getTransaction().begin();
		Article art = em.merge(new Article(name, content, id));
		em.getTransaction().commit();

		return art;
	}

}