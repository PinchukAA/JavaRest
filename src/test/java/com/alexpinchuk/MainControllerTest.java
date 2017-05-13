package com.alexpinchuk;

import com.alexpinchuk.controller.MainController;
import com.alexpinchuk.model.Article;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainControllerTest {

    private MainController mainController = new MainController();

    @Test
    public void addArticleTest(){
        Article article = new Article("name0", "content0", 0L);
        Article artFromDB = mainController.addArticle(article.getArticleName(), article.getArticleContent(), article.getId());

        assertEquals(article.getArticleName(), artFromDB.getArticleName());
        assertEquals(article.getId(), artFromDB.getId());
        assertEquals(article.getArticleContent(), artFromDB.getArticleContent());
    }

    @Test
    public void updateArticleTest(){
        Article article1 = new Article("name1", "content1", 1L);
        mainController.addArticle(article1.getArticleName(), article1.getArticleContent(), article1.getId());

        Article article2 = new Article("name2", "content2", 1L);
        Article artFromDB = mainController.updateArticle(article2.getArticleName(), article2.getArticleContent(), article2.getId());

        assertEquals(article2.getArticleName(), artFromDB.getArticleName());
        assertEquals(article2.getId(), artFromDB.getId());
        assertEquals(article2.getArticleContent(), artFromDB.getArticleContent());
    }

    @Test
    public void getArticleTest(){
        Article article = new Article("name0", "content0", 0L);
        mainController.addArticle(article.getArticleName(), article.getArticleContent(), article.getId());
        Article artFromDB = mainController.getArticle(article.getId());

        assertEquals(article.getArticleName(), artFromDB.getArticleName());
        assertEquals(article.getId(), artFromDB.getId());
        assertEquals(article.getArticleContent(), artFromDB.getArticleContent());
    }

    @Test
    public void deleteArticleTest(){
        Article article = new Article("name0", "content0", 0L);
        mainController.addArticle(article.getArticleName(), article.getArticleContent(), article.getId());
        mainController.deleteArticle(article.getId());

        try {
            Article artFromDB = mainController.getArticle(article.getId());
        }catch (Exception e){
            assertTrue("",true);
        }
    }
}
