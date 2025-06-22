package org.dormhub.www.service.dto;

import java.util.List;

public record PaginationDto<T>(
        List<T> items,
        Integer size,
        Integer page,
        Long total
) { }