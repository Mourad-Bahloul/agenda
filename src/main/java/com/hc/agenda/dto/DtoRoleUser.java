package com.hc.agenda.dto;

import com.hc.agenda.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoRoleUser {
    private Set<Role> roles;
}
