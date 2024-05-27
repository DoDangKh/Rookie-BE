package com.rookie.rookiee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "eusers")
public class Eusers extends AuditEntity implements UserDetails {

    public static final String WITH_RULES_GRAPH = "graph.eusers.roles";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotBlank
    private String firstName;

    @Column()
    @NotBlank
    private String lastName;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "eusers")
    private Set<Rates> rates;

    @OneToMany(mappedBy = "eusers")
    private Set<Carts> carts;

    @OneToMany(mappedBy = "eusers")
    private Set<Orders> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles != null) {
            return this.roles.stream().map(e -> new SimpleGrantedAuthority(e.getRolename())).toList();
        }
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return this.email;

    }

    @Override
    public boolean isAccountNonExpired() {

        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {

        throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }

    @Override
    public boolean isNew() {

        return true;
    }
}
