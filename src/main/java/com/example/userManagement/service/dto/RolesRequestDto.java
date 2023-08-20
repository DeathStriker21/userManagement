package com.example.userManagement.service.dto;

import com.example.userManagement.enumaration.RolesType;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesRequestDto {

    Set<RolesType> rolesType;
}
