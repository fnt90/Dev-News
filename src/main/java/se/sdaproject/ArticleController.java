package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) { this.articleRepository = articleRepository; }

    @RequestMapping("/articles")
    public List<Article> listAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }



}
