package org.dormhub.www.service.dto;

public record SearchDto(
        Integer page,
        Integer size,
        String query,
        OrderDto order
) {

    public record OrderDto(
            String field,
            String direction
    ) { }

}
