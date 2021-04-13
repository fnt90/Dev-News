package se.sdaproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;
    private String authorName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "articles")
    private List<Topic> topics;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "type")
    private List<Reaction> reactions;

    public Article() {

    }

    public Article(Long id, String title, String body, String authorName, List<Comment> comments, List<Topic> topics, List<Reaction> reactions) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorName = authorName;
        this.comments = comments;
        this.topics = topics;
        this.reactions = reactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setAuthorName() {
        this.authorName = authorName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
