package pl.teamformer.beans;

import pl.teamformer.beans.dictionaries.AccountDictionaryBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import pl.teamformer.dao.DaoAccount;
import pl.teamformer.model.Account;
import pl.teamformer.producers.ActualUser;
import pl.teamformer.tools.Messages;

@Data
@Named("loggedBean")
@SessionScoped
public class LoggedBean implements Serializable {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Inject
        @ActualUser
        private Account account;
        @Inject
        @Getter(AccessLevel.NONE)
        private DaoAccount dao;
        @Inject
        @Getter(AccessLevel.NONE)
        private AccountDictionaryBean adb;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @PostConstruct
        public void init() {
                adb.addActiveUser(account.getLogin());
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
}
