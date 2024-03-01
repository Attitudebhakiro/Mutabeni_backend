package com.mutabeni.store.security.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mutabeni.store.security.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
//@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "name"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})

public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotEmpty(message = "Name is required")
  private String name;

  @NotEmpty(message = "Phone number is required")
  private String contact;
  @NotEmpty(message = "Country and city are required")
  private String city;
  @NotEmpty(message = "Email is required")
  @Email
  private String email;
  @NotEmpty(message = "Password is required")
  private String password;
  @NotNull(message = "Role is required")
  @Enumerated(EnumType.STRING)
  private Role role;
  @JsonIgnore
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  public User(String name, String contact, String city, String email, String password, Role role, List<Token> tokens) {
    this.name = name;
    this.contact = contact;
    this.city = city;
    this.email = email;
    this.password = password;
    this.role = role;
    this.tokens = tokens;
  }

  public User(String name, String contact, String city, String email, String password, Role role) {
    this.name = name;
    this.contact = contact;
    this.city = city;
    this.email = email;
    this.password = password;
    this.role = role;
  }

public User(){}
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
