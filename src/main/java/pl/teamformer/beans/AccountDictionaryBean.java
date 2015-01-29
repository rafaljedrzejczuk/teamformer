package pl.teamformer.beans;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import pl.teamformer.dao.DaoAccount;
import pl.teamformer.data.Account;
import pl.teamformer.tools.Messages;

@Named
@Data
@ApplicationScoped
public class AccountDictionaryBean {

        private final List<Account> activeAccounts;
//        @Setter
        @Inject
        private DaoAccount dao;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public AccountDictionaryBean() {
                this.activeAccounts = new ArrayList();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void addActiveUser(String name) {
                if (!activeAccounts.contains(dao.getAccountByLogin(name))) {
                        activeAccounts.add(dao.getAccountByLogin(name));
                        return;
                }
                Messages.showMessageWarn("Ten użytkownik już posiadał aktywną sesję!");
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void deleteUnActiveUser(Account a) {
                activeAccounts.remove(a);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
