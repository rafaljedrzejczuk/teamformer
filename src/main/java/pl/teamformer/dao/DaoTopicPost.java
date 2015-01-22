package pl.teamformer.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.teamformer.data.Account;
import pl.teamformer.forum.Post;
import pl.teamformer.forum.Topic;
import pl.teamformer.forum.Topic.Category;
import lombok.*;

@Data
@Stateless
public class DaoTopicPost {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private String title = "Kliknij!";
        private String text = "Treść";
        private Category category;

        private List<Topic> topics;
        private Topic selectedTopic;

        @PersistenceContext
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                System.out.println("Tworze DAO TOPIC POST");
                readTopics();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PreDestroy
        public void destroy() {
                System.out.println("Niszcze DAO TOPIC POST");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void setCategoryFromString(String category) {
                this.category = Category.valueOf(category);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Post> getSelectedTopicPosts() {
                return getSelectedTopic().getPosts();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void readTopics() {
                setTopics((List<Topic>) getEntityManager().createNamedQuery("Topic.findAll").getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopicsByCategory(Topic.Category category) {
                Query query = getEntityManager().createNamedQuery("Topic.findByCategory").setParameter("category", category);
                List<Topic> topicsByCat = (List<Topic>) query.getResultList();

                //wybierz ostatnio edytowane tematy!
                List<Post> posts = new ArrayList();
                for (Topic t : topicsByCat)
                        if (t.getNewestPost() != null)
                                posts.add(t.getNewestPost());

                List<Topic> topicsByCat2 = new ArrayList();
                sortByDate(posts);

                for (Post p : posts)
                        topicsByCat2.add(p.getIdTopic());
                for (Topic t : topicsByCat2)
                        System.out.println(t.getDateAddedToString());
                return topicsByCat2;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addTopic(String title, String text, Account account, Category category) {
                System.out.println("First Post is created in Topic Class");
                Topic t = new Topic(title, text, account, category);
                getTopics().add(t);
                System.out.println("Merging a topic and first post..");
                System.out.println(category.name());
                setSelectedTopic(getEntityManager().merge(t));
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addPost(Account account) {
                Post p = new Post(getText(), account, getSelectedTopic());
                getSelectedTopic().addPost(p);
                System.out.println("Merging a post..");
                getEntityManager().merge(getSelectedTopic());
                refreshPosts(getSelectedTopic());
                System.out.println("Post merged!");
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
                Topic toRemove = getEntityManager().merge(t);
                System.out.println("Removing a topic..");
                getEntityManager().remove(toRemove);
        }
        /*&^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removePost(Post p) {
                Post toRemove = getEntityManager().merge(p);
                System.out.println("Removing a post..");
                getEntityManager().remove(toRemove);
                refreshPosts(getSelectedTopic());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void refreshPosts(Topic t) {
                if (t != null)
                        t.setPosts(getEntityManager().createNamedQuery("Post.findByTopicID").setParameter("idTopic", t).getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
