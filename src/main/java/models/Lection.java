package models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "lection")
public class Lection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy="lection", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<GroupLection> groupLections;

    public Lection() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<GroupLection> getGroupLections() {
        return groupLections;
    }

    public void setGroupLections(Collection<GroupLection> groupLections) {
        this.groupLections = groupLections;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 29 * result + (name != null ? name.hashCode() : 0);
        result = 29 * result + (groupLections != null ? groupLections.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || !(other instanceof Lection)) return false;
        Lection lection = (Lection) other;
        return (id != null ? id.equals(lection.id) : lection.id == null)
                && (name != null ? name.equals(lection.name) : lection.name == null)
                && (groupLections != null ? groupLections.equals(lection.groupLections) : lection.groupLections == null);
    }
}
