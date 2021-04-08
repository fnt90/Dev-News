package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    CommentRepository commentRepository;

    //View all comments on one article
    @GetMapping("/articles/{articleId}/comments")

    //View all comments by one author
    @GetMapping("/comments?authorName={authorName}")

    //Add new comment on article
    @PostMapping("/articles/{articleId}/comments")

    //Update a comment
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
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
