package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {

    CommentRepository commentRepository;
    ArticleRepository articleRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    //View all comments on one article
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<Comment>> listComments(@PathVariable Long articleId) {
        articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(commentRepository.findByArticleId(articleId));
    }

    //View all comments by one author
    @GetMapping(value = "/comments", params = {"authorName"})
    public ResponseEntity<List<Comment>> viewAllCommentsByAuthor(@RequestParam String authorName) {
        //commentRepository.findByAuthorName(authorName).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(commentRepository.findByAuthorName(authorName));
    }

    //Add new comment on article
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long articleId, @Valid @RequestBody Comment comment) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        comment.setArticle(article);
        commentRepository.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    //Update a comment
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody Comment updatedComment) {
        commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedComment.setId(id);
        Comment comment = commentRepository.save(updatedComment);
        return ResponseEntity.ok(comment);
    }

    //Delete a comment
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentRepository.delete(comment);
        return ResponseEntity.ok(comment);
    }


}
