package com.alexpinchuk.model;
import javax.persistence.*;

@Entity
@Table(name = "java")
public class Article {
    @Id
	@Column(name = "id")
	private Long id;
    @Column(name = "articleName")
	private String articleName;
    @Column(name = "articleContent")
	private String articleContent;
	
	public Article(){}

    public Article(String articleName, String articleContent, Long id){
	    this.id = id;
        this.articleName = articleName;
        this.articleContent = articleContent;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
}
