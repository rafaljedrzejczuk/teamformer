package pl.teamformer.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Data;
import pl.teamformer.model.Account;
import pl.teamformer.model.Post;
import pl.teamformer.model.Topic;

@Data
@Stateless
public class DaoPost {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PersistenceContext
        private EntityManager entityManager;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addPost(Account account, Topic topic, String text) {
                Post p = new Post(text, account, topic);
                System.out.println("1");
                topic.addPost(p);
                System.out.println("2");
                getEntityManager().merge(topic);
                System.out.println("3");
                getPostsByTopic(topic);
                System.out.println("4");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removePost(Topic topic, Post post) {
                Post toRemove = getEntityManager().merge(post);
                getEntityManager().remove(toRemove);
                getPostsByTopic(topic);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        //Do usunięcia, addPost juz 'aktualizuje' zawartosc tematu, a topic.xhtml pobiera posty metoda selTopic.getPosts()
        public void getPostsByTopic(Topic t) {
                if (t != null)
                        t.setPosts(getEntityManager().createNamedQuery("Post.findByTopicID").setParameter("idTopic", t).getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
