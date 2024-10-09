package com.homework2.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "settlement")
    private String settlement;

    @Column(name = "region")
    private String region;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @ToString.Exclude
    @OneToMany(mappedBy = "locality", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Attraction> attractions;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = (o instanceof HibernateProxy proxy) ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = (this instanceof HibernateProxy proxy) ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Locality locality = (Locality) o;
        return getId() != null && Objects.equals(getId(), locality.getId());
    }

    @Override
    public final int hashCode() {
        return (this instanceof HibernateProxy proxy) ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
