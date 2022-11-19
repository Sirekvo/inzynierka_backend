package inz.inzynierka.praca.entites;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;
    private String creator;
    private String comment;
    private Long serie_id;
    private String date;

    public CommentsEntity(Long comment_id, String creator, String comment, Long serie_id, String date) {
        this.comment_id = comment_id;
        this.creator = creator;
        this.comment = comment;
        this.serie_id = serie_id;
        this.date = date;
    }

    public CommentsEntity() {

    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getSerie_id() {
        return serie_id;
    }

    public void setSerie_id(Long serie_id) {
        this.serie_id = serie_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
