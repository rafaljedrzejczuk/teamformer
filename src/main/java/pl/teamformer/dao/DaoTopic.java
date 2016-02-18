package pl.teamformer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.teamformer.model.Account;
import pl.teamformer.model.Post;
import pl.teamformer.model.Topic;
import lombok.*;

@Data
@Stateless
public class DaoTopic {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PersistenceContext
        @Getter(AccessLevel.NONE)
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Topic addTopic(Topic t, Post p, Account account) {
                System.out.println("First Post is created in Topic Class");
                t.setIdOwner(account);
                p.setIdOwner(account);
                p.setIdTopic(t);
                t.addPost(p);
                System.out.println("Merging a topic and first post to " + t.getCategory().name() + "..");

                entityManager.persist(t);
                return t;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopics() {
                return entityManager.createQuery("SELECT t FROM Topic t").getResultList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopicsByCategory(Topic.Category category) {
                List<Topic> topicsByCat = (List<Topic>) entityManager.createQuery("SELECT t FROM Topic t WHERE t.category = :category")
                        .setParameter("category", category)
                        .getResultList();

                List<Post> posts = new ArrayList();
                topicsByCat.stream().filter((t) -> (t.getNewestPost() != null)).forEach((t) -> {
                        posts.add(t.getNewestPost());
                });

                List<Topic> topicsByCatSorted = new ArrayList();
                sortByDate(posts);

                posts.stream().forEach((p) -> {
                        topicsByCatSorted.add(p.getIdTopic());
                });
                return topicsByCatSorted;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void sortByDate(List<? extends Post> posts) {
                Collections.sort(posts, (Object o1, Object o2) -> {
                        Post p1 = (Post) o1;
                        Post p2 = (Post) o2;

                        if (p1.getAdded() == p2.getAdded())
                                return 0;
                        else if (p1.getAdded().getTime() > p2.getAdded().getTime())
                                return -1;
                        else
                                return 0;
                });
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeTopic(Topic t) {
                Topic toRemove = (Topic) entityManager.createQuery("SELECT t FROM Topic t WHERE t.id = :id")
                        .setParameter("id", t.getId())
                        .getSingleResult();
                entityManager.remove(toRemove);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
