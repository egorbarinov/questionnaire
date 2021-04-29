package ru.egorbarinov.questionnaire.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.egorbarinov.questionnaire.dto.admin.RoleDto;
import ru.egorbarinov.questionnaire.service.RoleService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/roles")
@RestController
public class RoleRestController {

    private final RoleService roleService;

    @GetMapping
    public List<RoleDto> getRoles() {
        log.info("Get all roles");
        return roleService.getRoles();
    }

    @GetMapping("/{id}")
    public RoleDto getRole(@PathVariable("id") UUID id) {
        log.info("Get role by id {}", id);
        return roleService.getRoleById(id);
    }
}
