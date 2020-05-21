package fii.ap.entity;

import javax.persistence.*;

@Entity
@Table(name = "Players", schema = "DBA_IP")
@SequenceGenerator(name="players_seq", allocationSize = 1)
public class Player {

    private Integer id;
    private String name;
    private Integer game_id;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="players_seq")
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

    @Basic
    @Column(name = "GAME_ID")
    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }
}