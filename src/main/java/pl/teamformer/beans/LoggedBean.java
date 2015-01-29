package pl.teamformer.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.SessionContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import pl.teamformer.dao.DaoAccount;
import pl.teamformer.dao.DaoTopicPost;
import pl.teamformer.data.Account;
import pl.teamformer.forum.Post;
import pl.teamformer.forum.Topic;
import pl.teamformer.tools.Messages;

@Data
@Named("loggedBean")
@SessionScoped
public class LoggedBean implements Serializable {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private String password = null;
        private String login = null;
        private Account account;
        private Account selectedAccount;

        @Inject
        @Getter(AccessLevel.NONE)
        private SessionContext sessionContext;
        @Inject
        @Getter(AccessLevel.NONE)
        private DaoAccount dao;
        @Inject
        private DaoTopicPost daoTP;
        @Inject
        @Getter(AccessLevel.NONE)
        private AccountDictionaryBean adb;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                account = dao.getAccountByLogin(getName());
                adb.addActiveUser(account.getLogin());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopics() {
                return daoTP.getTopics();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public List<Topic> getTopicsBy(Topic.Category category) {
                return daoTP.getTopicsByCategory(category);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void logOut() {
                adb.deleteUnActiveUser(account);
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.invalidateSession();
                Messages.redirectWithMessage("Zostałeś wylogowany!");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void ifLoggedIn() {
                if (!adb.getActiveAccounts().contains(account))
                        logOut();
                System.out.println("Jesteś zalogowany!");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addTopic() {
                daoTP.addTopic(daoTP.getTitle(), daoTP.getText(), account, daoTP.getCategory());
                daoTP.setTitle("Tytuł");
                daoTP.setText("Treść");

                Messages.redirectWithMessage("admin/topic.xhtml", "Utworzono nowy wątek!");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addPost() {
                daoTP.addPost(account);
                daoTP.setText("Treść");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void toTopic(Topic t) {
                daoTP.setSelectedTopic(t);
                daoTP.refreshPosts(t);
                Messages.redirectWithMessage("admin/topic.xhtml", t.getTitle());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void toAddTopic() {
                Messages.redirect("admin/addtopic.xhtml");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public String getName() {
                return sessionContext.getCallerPrincipal().getName();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removeTopic(Topic t) {
                daoTP.removeTopic(t);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void removePost(Post p) {
                daoTP.removePost(p);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
