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
    private String header;
    private String title;
    private String creator;
    private String genre;
    private String production;
    private Date premiere;
    private String description;

    public SeriesEntity(Long series_id, String header, String title, String creator, String genre, String production, Date premiere, String description) {
        this.series_id = series_id;
        this.header = header;
        this.title = title;
        this.creator = creator;
        this.genre = genre;
        this.production = production;
        this.premiere = premiere;
        this.description = description;
    }

    public SeriesEntity() {

    }

    public Long getSeries_id() {
        return series_id;
    }

    public void setSeries_id(Long series_id) {
        this.series_id = series_id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public Date getPremiere() {
        return premiere;
    }

    public void setPremiere(Date premiere) {
        this.premiere = premiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
