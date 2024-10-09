package com.homework2.models;

import com.homework2.enums.TypeOfService;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "assistance")
@AllArgsConstructor
public class Assistance {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "executor")
    private String executor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_of_service")
    private TypeOfService typeOfService;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "assistance_attractions",
            joinColumns = @JoinColumn(name = "assistance_id"),
            inverseJoinColumns = @JoinColumn(name = "attractions_id")
    )
    private List<Attraction> attractions;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = (o instanceof HibernateProxy proxy) ? proxy.getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = (this instanceof HibernateProxy proxy) ? proxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Assistance that = (Assistance) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return (this instanceof HibernateProxy proxy) ? proxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
