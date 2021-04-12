package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    /*List<Topic> findByArticleId(Long articleID);*/
}
