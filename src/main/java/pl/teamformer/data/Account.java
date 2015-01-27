package pl.teamformer.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.Data;
import pl.teamformer.tools.DateFormatters;

@Data
@Entity
@Table(name = "ACCOUNT")
@SequenceGenerator(allocationSize = 1, name = "ACCOUNT_GEN", sequenceName = "ACCOUNT_ID")
@NamedQueries({
        @NamedQuery(name = "Account.findAll", query = "SELECT ac FROM Account ac"),
        @NamedQuery(name = "Account.findByLogin", query = "SELECT ac FROM Account ac WHERE ac.login = :login")})
public class Account implements Serializable {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_GEN")
        @NotNull
        @Column(name = "ID")
        private Long id;

        @Column(name = "LOGIN", nullable = false, unique = true, length = 30)
        private String login;

        @Column(name = "PASSWORD", nullable = false)
        private String password;

        @Column(name = "EMAIL", nullable = false, unique = true)
        private String email;

        @Enumerated(EnumType.STRING)
        private Status status = Status.INACTIVATED;

        @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
        @JoinColumn(name = "ID_TEAM", referencedColumnName = "ID")
        private Team idTeam;

        @Enumerated(EnumType.STRING)
        private Team_Position teamPosition = Team_Position.ALL;

        @Enumerated(EnumType.STRING)
        private Team_Rights teamRights = Team_Rights.FREE;

        @Column(name = "WARN_LEVEL")
        private Integer warn;

        @Temporal(value = TemporalType.DATE)
        private final Date dateAdded;

        @Temporal(value = TemporalType.DATE)
        private Date lastVisited;

        @Enumerated(EnumType.STRING)
        private Logged_Info loggedInfo = Logged_Info.LOGGED_OUT;

        @Column(name = "POSTS")
        private Integer postsCount;

        @Column(name = "AVAILABLE_ACTIONS")
        private Integer actions;

//        @OneToOne(cascade = CascadeType.ALL)
//        @JoinColumn(name = "ID_USERGROUP", referencedColumnName = "ID")
//        private final UserGroup idGroup;
        @Column(name = "GROUPNAME")
        private String group;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account() {
                this.email = "dsa";
                this.password = "aha";
                this.login = "dsadas";

                this.actions = 6;
                this.postsCount = 0;
                this.warn = 0;

                this.dateAdded = new Date();
                this.lastVisited = new Date();
                this.group = "ADMIN";

//                this.idGroup = new UserGroup(login);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Account(String login, String password, String email) {
                this.login = login;
                this.password = password;
                this.email = email;

                this.actions = 6;
                this.postsCount = 0;
                this.warn = 0;

                this.dateAdded = new Date();
                this.lastVisited = new Date();
                this.group = "ADMIN";

//                this.idGroup = new UserGroup(login);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public String getDateAddedToString() {
                if (dateAdded == null)
                        return "null";
                return DateFormatters.SDF_DATE.format(dateAdded.getTime());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Override
        public boolean equals(Object other) {
                if ((other instanceof Account) && (id != null))
                        return id.equals(((Account) other).getId());
                return other == this;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Override
        public int hashCode() {
                int hash = 0;
                hash += (id != null ? id.hashCode() : 0);
                return hash;
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/

        public enum Status {

                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
                INACTIVATED, ACTIVATED, BANNED;
                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/

        public enum Logged_Info {

                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
                LOGGED_IN, LOGGED_OUT;
                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/

        public enum Team_Rights {

                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
                MEMBER, MODERATOR, ADMIN, FREE;
                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/

        public enum Team_Position {

                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
                GK, DEF, MID, AT,
                ALL;
                /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
