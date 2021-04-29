package ru.egorbarinov.questionnaire.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "questionnaire", name = "user")
public class User implements BaseEntity {

    @Id
    @GeneratedValue
    @Type(type="pg-uuid")
    private UUID id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column
    private String username;

    @NotNull
    @Column
    private String password;

    @Column
    private String email;

    @NotNull
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "questionnaire", name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
