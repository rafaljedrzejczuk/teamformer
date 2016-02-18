package pl.teamformer.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import pl.teamformer.dao.DaoAccount;
import pl.teamformer.model.Account;
import pl.teamformer.model.Account.TeamPosition;

@Data
@Named
@RequestScoped
public class AccountSettingsBean {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private String avatarURL = "";
        private String password = "";
        private String email = "";
        private TeamPosition position;

        @Inject
        @Getter(AccessLevel.NONE)
        private LoggedBean lb;
        @Inject
        @Getter(AccessLevel.NONE)
        private DaoAccount dao;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public void save() {
                Account dbAccount = lb.getAccount();

                saveEmail(dbAccount);
                savePassword(dbAccount);
                saveTeamPosition(dbAccount);

                dao.getAccounts().remove(dbAccount);
                dao.getAccounts().add(dbAccount);

                lb.setAccount(dbAccount);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void savePassword(Account a) {
                if (!"".equals(password)) {
                        a.setPassword(password);
                        password = "";
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void saveEmail(Account a) {
                if (!"".equals(email)) {
                        a.setEmail(email);
                        email = "";
                }
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private void saveTeamPosition(Account a) {
                a.setTeamPosition(position);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public TeamPosition[] getTeamPositions() {
                return TeamPosition.values();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
