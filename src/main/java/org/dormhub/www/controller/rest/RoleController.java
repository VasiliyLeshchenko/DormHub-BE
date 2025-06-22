package org.dormhub.www.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dormhub.www.controller.rest.dto.rs.RoleResponse;
import org.dormhub.www.controller.rest.mapper.RoleWebMapper;
import org.dormhub.www.service.RoleService;
import org.dormhub.www.service.dto.RoleDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.dormhub.www.util.UrlConstants.API_ROLES;
import static org.dormhub.www.util.UrlConstants.BASE_API_V1;


@Slf4j
@RestController
@RequestMapping(BASE_API_V1 + API_ROLES)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;
    private final RoleWebMapper mapper;

    @GetMapping
    public List<RoleResponse> getAll() {
        log.info("Received request to get all roles");
        List<RoleDto> roles = service.getAll();
        return mapper.toResponse(roles);
    }

}
