package com.example.userManagement.entity;

import com.example.userManagement.service.dto.UserUpdateRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_Name")
    public String lastName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RolesEntity> roles = new HashSet<>();

    public UserEntity update(UserUpdateRequestDto userUpdateRequestDto) {
        return this.toBuilder()
                .firstName(userUpdateRequestDto.getFirstName())
                .lastName(userUpdateRequestDto.getLastName())
                .build();
    }
}
