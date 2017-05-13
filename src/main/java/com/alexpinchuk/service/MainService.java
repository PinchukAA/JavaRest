package com.alexpinchuk.service;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.alexpinchuk.controller.MainController;
import com.alexpinchuk.model.Article;
import com.sun.jersey.api.NotFoundException;
import org.apache.log4j.Logger;

@Path("/articles")
public class MainService {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	private Logger logger = Logger.getLogger(MainService.class);
	private MainController mainController;
	
	public MainService(){
		mainController = new MainController();
	}

    /**
     * Return all existing article names in JSON.
     * @return set of string values of all existing article names.
     */
	@GET
	@Produces("application/json;charset=UTF-8")
	public List<Article> getArticles(){
		return mainController.getArticles();
	}

    /**
     * Return article with a current name in JSON;
     * @param id the name of teh article that should be found;
     * @return article from DB with current name in JSON.
     */
	@GET
	@Path("{id}")
	@Produces("application/json;charset=UTF-8")
	public Article getArticle(@PathParam("id") Long id){
        Article art = mainController.getArticle(id);
        try {
            if (art == null) throw new NotFoundException("No such article!");
        } catch (NotFoundException e){
	        logger.error(e);
        }

        return art;
	}

    /**
     * Replace article with current name.
     * @param id name of the article that should be update.
     * @param article new article that will replace one with current name.
     */
	@PUT
	@Path("{id}")
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
	public Article putArticle(@PathParam("id") Long id, Article article){
		Article art = mainController.updateArticle(article.getArticleName(), article.getArticleContent(), id);
        try {
            if (art == null) throw new NotFoundException("No such article!");
        } catch (NotFoundException e){
            logger.error(e);
        }
        return art;
	}

    /**
     * Add new article.
     * @param article new article that will be added to the DB
     */
	@POST
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public Article newArticle(Article article){
		Article art = mainController.addArticle(article.getArticleName(), article.getArticleContent(), article.getId());
		try {
            if (art == null) throw new NotFoundException("Article adding fail!");
        } catch (NotFoundException e){
            logger.error(e);
        }

        return art;
	}

    /**
     * Delete an existing article with a current name.
     * @param id name of the article that will be deleted.
     */
    @DELETE
    @Path("{id}")
    public void deleteContact(@PathParam("id") Long id) {
        mainController.deleteArticle(id);
    }
	
}