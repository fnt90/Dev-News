package se.sdaproject;

import org.aspectj.lang.annotation.DeclareError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {

    TopicRepository topicRepository;
    ArticleRepository articleRepository;

    public TopicController(TopicRepository topicRepository, ArticleRepository articleRepository) {
        this.topicRepository = topicRepository;
        this.articleRepository = articleRepository;
    }

    //List all topics
    //@GetMapping("/topics")

    //List all topics associated with one article
    //@GetMapping("/articles/{articleId}/topics")

    //Create new topic
    @PostMapping("/topics")
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        topicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    //Associate topic with one article (may also create that topic if nonexistent)
    @PostMapping("/articles/{articleId}/topics/{topicId}")
    public ResponseEntity<Topic> associateTopic(@PathVariable Long articleId, @PathVariable Long topicId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        topic.getArticles().add(article);
        topicRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    //Update topic
    //@PutMapping("/topics/{id}")

    //Delete a topic
    //@DeleteMapping("/topics/{id}")

    //Delete association of a topic for one article
    @DeleteMapping("/articles/{articleId}/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAssociation(@PathVariable Long topicId, @PathVariable Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        if (topic.getArticles().contains(article)) {
            topic.getArticles().remove(article);
            topicRepository.save(topic);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    //List all articles associated with one topic
    @GetMapping("/topics/{topicId}/articles")
    public ResponseEntity<List<Article>> listArticlesInTopic(@PathVariable Long topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        List<Article> articles = topic.getArticles();
        return ResponseEntity.ok(articles);
    }

    /*GET	/topics
    * GET	/articles/{articleId}/topics
    *POST	/articles/{articleId}/topics
    *POST	/topics
    *PUT	/topics/{id}
    *DELETE	/topics/{id}
    *DELETE	/articles/{articleId}/topics/{topicId}
    *GET	/topics/{topicId}/articles
    *
    * */

}
