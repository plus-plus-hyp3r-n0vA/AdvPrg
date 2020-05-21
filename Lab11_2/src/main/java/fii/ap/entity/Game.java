package fii.ap.entity;

import javax.persistence.*;

@Entity
@Table(name = "Games", schema = "DBA_IP")
public class Game {

    private Integer id;
    private String name;

    @Id
    @Column(name = "ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
