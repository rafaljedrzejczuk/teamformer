package pl.teamformer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
                return entityManager.createNamedQuery("Topic.findAll").getResultList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopicsByCategory(Topic.Category category) {
                Query query = entityManager.createNamedQuery("Topic.findByCategory").setParameter("category", category);
                List<Topic> topicsByCat = (List<Topic>) query.getResultList();

                //wybierz ostatnio edytowane tematy!
                List<Post> posts = new ArrayList();
                for (Topic t : topicsByCat)
                        if (t.getNewestPost() != null)
                                posts.add(t.getNewestPost());

                List<Topic> topicsByCatSorted = new ArrayList();
                sortByDate(posts);

                for (Post p : posts)
                        topicsByCatSorted.add(p.getIdTopic());
                for (Topic t : topicsByCatSorted)
                        System.out.println(t.getDateAddedToString());
                return topicsByCatSorted;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void sortByDate(List<? extends Post> posts) {
                Collections.sort(posts, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                                Post p1 = (Post) o1;
                                Post p2 = (Post) o2;

                                if (p1.getDateAdded().getTime() + p1.getHourAdded().getTime() == p2.getDateAdded().getTime() + p2.getHourAdded().getTime())
                                        return 0;
                                else if (p1.getDateAdded().getTime() + p1.getHourAdded().getTime() > p2.getDateAdded().getTime() + p2.getHourAdded().getTime())
                                        return -1;
                                else
                                        return 0;
                        }
                });
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeTopic(Topic t) {
                Topic toRemove  = (Topic) entityManager.createNamedQuery("Topic.findById").setParameter("id", t.getId()).getSingleResult();
                System.out.println("Removing a topic..");
                entityManager.remove(toRemove);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
