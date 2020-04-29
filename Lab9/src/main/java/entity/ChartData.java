package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CHART_DATA", schema = "DBA_IP")
public class ChartData {
    private long id;
    private long rank;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RANK")
    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChartData that = (ChartData) o;
        return id == that.id &&
                rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rank);
    }
}
