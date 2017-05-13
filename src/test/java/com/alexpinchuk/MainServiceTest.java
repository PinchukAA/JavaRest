package com.alexpinchuk;

import com.alexpinchuk.controller.MainController;
import com.alexpinchuk.model.Article;
import com.alexpinchuk.service.MainService;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MainService.class })
public class MainServiceTest extends JerseyTest {

    public MainServiceTest() throws Exception{
        super("com.alexpinchuk");
    }

    @Test
    public void getArticleTest() throws Exception {
        Article article = new Article("name0", "content0", 0L);

        MainController controller = PowerMockito.mock(MainController.class);
        whenNew(MainController.class).withAnyArguments().thenReturn(controller);
        when(controller.getArticle(0L)).thenReturn(article);

        WebResource webResource = resource();
        WebResource.Builder builder = webResource.path("articles").path("0").getRequestBuilder();
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        builder.accept(MediaType.APPLICATION_JSON_TYPE);
        Article resArt = builder.get(new GenericType<Article>(){});

        assertEquals(article.getArticleName(), resArt.getArticleName());
        assertEquals(article.getId(), resArt.getId());
        assertEquals(article.getArticleContent(), resArt.getArticleContent());

    }

    @Test
    public void getArticlesTest() throws Exception {
        List<Article> articles = new ArrayList<Article>();
        articles.add( new Article("name0", "content0", 0L));
        articles.add( new Article("name1", "content1", 1L));
        articles.add( new Article("name2", "content2", 2L));

        MainController controller = PowerMockito.mock(MainController.class);
        whenNew(MainController.class).withAnyArguments().thenReturn(controller);
        when(controller.getArticles()).thenReturn(articles);

        WebResource webResource = resource();
        WebResource.Builder builder = webResource.path("articles").getRequestBuilder();
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        builder.accept(MediaType.APPLICATION_JSON_TYPE);
        List<Article> resArts = builder.get(new GenericType<ArrayList<Article>>(){});


        assertEquals(articles.get(0).getArticleName(), resArts.get(0).getArticleName());
        assertEquals(articles.get(1).getId(), resArts.get(1).getId());
        assertEquals(articles.get(2).getArticleContent(), resArts.get(2).getArticleContent());
    }

    @Test
    public void postArticleTest() throws Exception {
        Article consArticle = new Article("name0", "content0", 0L);
        Article prodArticle = new Article("name1", "content1", 1L);

        MainController controller = PowerMockito.mock(MainController.class);
        whenNew(MainController.class).withAnyArguments().thenReturn(controller);
        when(controller.addArticle(consArticle.getArticleName(), consArticle.getArticleContent(), consArticle.getId())).thenReturn(prodArticle);

        WebResource webResource = resource();
        WebResource.Builder builder = webResource.path("articles").getRequestBuilder();
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        builder.accept(MediaType.APPLICATION_JSON_TYPE);
        ClientResponse response = builder.post(ClientResponse.class, consArticle);
        Article resArt = response.getEntity(Article.class);

        assertEquals(prodArticle.getArticleName(), resArt.getArticleName());
        assertEquals(prodArticle.getId(), resArt.getId());
        assertEquals(prodArticle.getArticleContent(), resArt.getArticleContent());
    }

    @Test
    public void putArticleTest() throws Exception {
        Article consArticle = new Article("name0", "content0", 0L);
        Article prodArticle = new Article("name1", "content1", 1L);

        MainController controller = PowerMockito.mock(MainController.class);
        whenNew(MainController.class).withAnyArguments().thenReturn(controller);
        when(controller.updateArticle(consArticle.getArticleName(), consArticle.getArticleContent(), consArticle.getId())).thenReturn(prodArticle);

        WebResource webResource = resource();
        WebResource.Builder builder = webResource.path("articles").path("0").getRequestBuilder();
        builder.type(MediaType.APPLICATION_JSON_TYPE);
        builder.accept(MediaType.APPLICATION_JSON_TYPE);
        ClientResponse response = builder.put(ClientResponse.class, consArticle);
        Article resArt = response.getEntity(Article.class);

        assertEquals(prodArticle.getArticleName(), resArt.getArticleName());
        assertEquals(prodArticle.getId(), resArt.getId());
        assertEquals(prodArticle.getArticleContent(), resArt.getArticleContent());
    }



}
