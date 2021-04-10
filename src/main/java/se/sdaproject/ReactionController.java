package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReactionController {

    //Structure should be similar to topic, but the owning side should be article/comment?

    ReactionRepository reactionRepository;
    ArticleRepository articleRepository;
    CommentRepository commentRepository;

    public ReactionController(ReactionRepository reactionRepository, ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.reactionRepository = reactionRepository;
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    //View all created reactions
    @GetMapping("/reactions")
    public List<Reaction> listAllReactions() {
        List<Reaction> reactions = reactionRepository.findAll();
        return reactions;
    }
    //View all reactions on an article
    //@GetMapping("/article/{articleId}/reactions")

    //View all reactions on a comment
    //@GetMapping("/comments/{commentId}/reactions")

    //Create new reaction type
    @PostMapping("/reactions")
    public ResponseEntity<Reaction> createReaction(@RequestBody Reaction reaction) {
        reactionRepository.save(reaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(reaction);
    }

    //Add reaction to article
    @PostMapping("/articles/{articleId}/reactions/{reactionId}")
    public ResponseEntity<Reaction> reactToArticle(@PathVariable Long articleId, @PathVariable Long reactionId) {
        Article article = articleRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        Reaction reaction = reactionRepository.findById(reactionId).orElseThrow(ResourceNotFoundException::new);
        reaction.setArticle(article);
        reactionRepository.save(reaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(reaction);
    }

    //Add reaction to comment
    @PostMapping("/comments/{commentId}/reactions/{reactionId}")
    public ResponseEntity<Reaction> reactToComment(@PathVariable Long commentId, @PathVariable Long reactionId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(ResourceNotFoundException::new);
        Reaction reaction = reactionRepository.findById(reactionId).orElseThrow(ResourceNotFoundException::new);
        reaction.setComment(comment);
        reactionRepository.save(reaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(reaction);
    }
    //Update reaction to article
    //@PutMapping

    //Update reaction to comment
    //@PutMapping

    //Delete reaction to article
    //@DeleteMapping("/articles/{articleId}/reactions/{reactionId}")

    //Delete reaction to comment
    //@DeleteMapping("/comments/{commentId}/reactions/{reactionId}")

}
