package ru.egorbarinov.questionnaire.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDetailsDto implements BaseDTO, UserDetails {

  private UUID id;
  private String firstName;
  private String lastName;
  private String username;
  private String password;
  private String repeatPassword;
  private String phone;
  private String email;
  private Boolean isDeleted;
  private Set<RoleDto> roles;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
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
    return !isDeleted;
  }

}
