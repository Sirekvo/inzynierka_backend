package inz.inzynierka.praca.entites;

import javax.persistence.*;

@Entity
@Table(name = "sliders")
public class SlidersEntity {

    private String url;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slider_id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long position;

    public SlidersEntity(String url, Long slider_id, Long position) {
        this.url = url;
        this.slider_id = slider_id;
        this.position = position;
    }

    public SlidersEntity() {

    }

    public Long getSlider_id() {
        return slider_id;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
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
