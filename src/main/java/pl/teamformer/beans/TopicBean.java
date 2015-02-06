package pl.teamformer.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import pl.teamformer.dao.DaoPost;
import pl.teamformer.dao.DaoTopic;
import pl.teamformer.model.Post;
import pl.teamformer.model.Topic;
import pl.teamformer.tools.Messages;

@Data
@Named
@SessionScoped
public class TopicBean implements Serializable {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private Topic selectedTopic = new Topic();
        private Post firstPost = new Post();

        @Inject
        @Getter(AccessLevel.NONE)
        private LoggedBean lb;
        @Inject
        @Getter(AccessLevel.NONE)
        private DaoTopic daoTopic;
        @Inject
        @Getter(AccessLevel.NONE)
        private DaoPost daoPost;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopics() {
                return daoTopic.getTopics();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopicsBy(Topic.Category category) {
                return daoTopic.getTopicsByCategory(category);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Post> getSelectedTopicPosts() {
                return selectedTopic.getPosts();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addTopic() {
                selectedTopic = daoTopic.addTopic(selectedTopic, firstPost, lb.getAccount());
                Messages.redirectWithMessage("admin/topic.xhtml", "Utworzono nowy wątek!");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeTopic(Topic t) {
                daoTopic.removeTopic(t);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void toTopic(Topic t) {
                setSelectedTopic(t);
                daoPost.getPostsByTopic(t);
                Messages.redirectWithMessage("admin/topic.xhtml", t.getTitle());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void toAddTopic() {
                Topic.Category temp = selectedTopic.getCategory();
                selectedTopic = new Topic();
                selectedTopic.setCategory(temp);
                Messages.redirect("admin/addtopic.xhtml");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void setCategoryFromString(String category) {
                this.selectedTopic.setCategory(Topic.Category.valueOf(category));
                System.out.println(category + " : " + selectedTopic.getCategory());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
