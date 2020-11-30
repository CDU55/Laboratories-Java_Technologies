package DataLayerJPA;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Academic")
public class AcademicMeeting extends MeetingsJPA {

    private String required_rank;

    public String getRequired_rank() {
        return required_rank;
    }

    public void setRequired_rank(String required_rank) {
        this.required_rank = required_rank;
    }
}
