package at.htl.nfconnect.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Elias Salfinger on 17/12/15.
 */
@Entity
@Table(name = "PICTURE")
public class Picture implements Serializable {
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] image;
    //endregion

    //region Constructors
    public Picture(byte[] image) {
        this.image = image;
    }

    public Picture() {
    }
    //endregion

    //region Getters and Setters
    public Long getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    //endregion
}
