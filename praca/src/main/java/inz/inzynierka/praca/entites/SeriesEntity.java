package inz.inzynierka.praca.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "series")
public class SeriesEntity {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long series_id;
    private String title;
    private String creator;
    private String genre;
    private String production;
    private String premiere;
    private String description;
    private String url;
    private String post_creator;
    private String creation_date;

    public SeriesEntity(Long series_id, String title, String creator, String genre, String production, String premiere, String description, String url, String post_creator, String creation_date) {
        this.series_id = series_id;
        this.title = title;
        this.creator = creator;
        this.genre = genre;
        this.production = production;
        this.premiere = premiere;
        this.description = description;
        this.url = url;
        this.post_creator = post_creator;
        this.creation_date = creation_date;
    }

    public SeriesEntity() {

    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getPost_creator() {
        return post_creator;
    }

    public void setPost_creator(String post_creator) {
        this.post_creator = post_creator;
    }

    public Long getSeries_id() {
        return series_id;
    }

    public void setSeries_id(Long series_id) {
        this.series_id = series_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
