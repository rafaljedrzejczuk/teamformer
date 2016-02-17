package pl.teamformer.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.teamformer.tools.DateFormatters;

@Data
@EqualsAndHashCode
@Entity
@Table(schema = "teamformer")
public class Post extends AbstractEntity {

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
}
