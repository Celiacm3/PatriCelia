package ufv.extraordinaria.dis.CCM.Clases;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DatosTweets {
    private String id;

    private String usuario;
    private String tweet;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date fecha;

    public DatosTweets(){}

    public DatosTweets(String id, String usuario, String tweet, Date fecha) {
        this.id = id;
        this.usuario = usuario;
        this.tweet=tweet;
        this.fecha = fecha;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
