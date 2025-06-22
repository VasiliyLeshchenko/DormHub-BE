package org.dormhub.www.controller.rest.dto.rs;

import java.util.List;

public record PaginationResponse<T>(
        List<T> items,
        Integer size,
        Integer page,
        Long total
) { }