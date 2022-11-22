package inz.inzynierka.praca.entites;

import javax.persistence.*;

@Entity
@Table(name = "sliders")
public class SlidersEntity {

    private String url;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slider_id;



    public SlidersEntity(String url, Long slider_id) {
        this.url = url;
        this.slider_id = slider_id;
    }

    public SlidersEntity() {

    }

    public Long getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(Long slider_id) {
        this.slider_id = slider_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
