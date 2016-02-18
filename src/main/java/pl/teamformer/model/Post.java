package pl.teamformer.model;

import java.util.Date;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.teamformer.tools.DateFormatters;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(schema = "teamformer")
public class Post extends AbstractEntity {

        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Size(min = 10, max = 255)
        private String text;

        @OneToOne(cascade = {CascadeType.MERGE})
        @JoinColumn(name = "ID_OWNER", referencedColumnName = "ID", nullable = false)
        private Account idOwner;

        @ManyToOne(cascade = {CascadeType.MERGE})
        @JoinColumn(name = "ID_TOPIC", referencedColumnName = "ID", nullable = false)
        private Topic idTopic;

        @Temporal(value = TemporalType.DATE)
        private Date added;
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
        public String getDateAddedToString() {
                return DateFormatters.SDF_DATE.format(added) + " at " + DateFormatters.SDF_HOUR.format(added);
        }
        /*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
}
