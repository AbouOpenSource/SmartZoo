package esi.univbobo.bf.smartzoo;

import java.sql.Date;

/**
 * Created by Abou on 12/10/2018.
 */

public class AnimalRecord {
    private String espece;
    private String pseudo;
    private byte[] image;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    AnimalRecord(int id, String pseudo, String espece, byte[] image) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setEspece(espece);
        this.setImage(image);
    }
    AnimalRecord(int id, byte[] image) {
        this.setId(id);
        this.setImage(image);
    }

    public AnimalRecord(String espece, String pseudo, byte[] image) {
        this.espece = espece;
        this.pseudo = pseudo;
        this.image = image;
    }

    AnimalRecord(int id, String pseudo, String espece) {
        this.setId(id);
        this.setPseudo(pseudo);
        this.setEspece(espece);
    }

    public String getEspece() {
        return espece;
    }

    public String getPseudo() {
        return pseudo;
    }

    public byte[] getImage()
    {

        return image;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}