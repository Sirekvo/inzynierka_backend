package inz.inzynierka.praca.entites;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sliders")
public class SlidersEntity {
    @Id
    private String url;

    public SlidersEntity(String url) {
        this.url = url;
    }

    public SlidersEntity() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
