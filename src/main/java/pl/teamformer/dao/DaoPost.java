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
        public void addPost(Account account, Topic topic, Post p) {
                p.setIdOwner(account);
                p.setIdTopic(topic);
                System.out.println("1");
                topic.addPost(p);
                System.out.println("2");
                getEntityManager().persist(p);
                System.out.println("3");
                getPostsByTopic(topic);
                System.out.println("4");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removePost(Topic topic, Post post) {
                Post toRemove  = (Post) entityManager.createNamedQuery("Post.findById").setParameter("id", post.getId()).getSingleResult();
                getEntityManager().remove(toRemove);
                getPostsByTopic(topic);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void getPostsByTopic(Topic t) {
                if (t != null)
                        t.setPosts(getEntityManager().createNamedQuery("Post.findByTopicId").setParameter("idTopic", t).getResultList());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
