package ru.egorbarinov.questionnaire.dto.admin;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ru.egorbarinov.questionnaire.dto.BaseDTO;

import java.util.UUID;
@Data
public class RoleDto implements BaseDTO, GrantedAuthority {

  private UUID id;
  private String name;

  @Override
  public String getAuthority() {
    return name;
  }
}
