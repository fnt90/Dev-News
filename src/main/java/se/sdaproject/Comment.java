package se.sdaproject;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String body;

    @NotBlank
    @Column(nullable = false)
    private String authorName;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(nullable = false)
   // @NotNull
   // @JoinColumn(name = "article_id")
    private Article article;

/*    public Comment() {
    }*/

    /*public Comment(String body, String authorName, Article article) { //TODO check if correct
        this.body = body;
        this.authorName = authorName;
        //TODO: check about article
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
