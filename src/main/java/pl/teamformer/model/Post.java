package pl.teamformer.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import pl.teamformer.tools.DateFormatters;

@Data
@Entity
@Table(name = "POST")
@SequenceGenerator(allocationSize = 1, name = "POST_GEN", sequenceName = "POST_ID")
@NamedQueries({
        @NamedQuery(name = "Post.findAll", query = "SELECT po FROM Post po"),
        @NamedQuery(name = "Post.findByTopicId", query = "SELECT po FROM Post po WHERE po.idTopic = :idTopic"),
        @NamedQuery(name = "Post.findById", query = "SELECT po FROM Post po WHERE po.id = :id")})
public class Post implements Serializable {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "POST_GEN")
        @Column(name = "ID")
        private Long id;

        @Column(name = "TEXT", length = 255)
        private String text;

        @OneToOne(cascade = {CascadeType.MERGE})
        @JoinColumn(name = "ID_OWNER", referencedColumnName = "ID", nullable = false)
        private Account idOwner;

        @ManyToOne(cascade = {CascadeType.MERGE})
        @JoinColumn(name = "ID_TOPIC", referencedColumnName = "ID", nullable = false)
        private Topic idTopic;

        @Temporal(value = TemporalType.DATE)
        private Date dateAdded;

        @Temporal(value = TemporalType.TIME)
        private Date hourAdded;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public Post() {
                this.text = "Treść";

                this.dateAdded = new Date();
                this.hourAdded = new Date();
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public String getDateAddedToString() {
                return DateFormatters.SDF_DATE.format(getDateAdded()) + " at " + DateFormatters.SDF_HOUR.format(getHourAdded());
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Override
        public boolean equals(Object other) {
                if ((other instanceof Post) && (id != null))
                        return id.equals(((Post) other).getId());
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
}
