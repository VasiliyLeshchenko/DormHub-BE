package org.dormhub.www.controller.rest.dto.rq;


public record SearchRequest(
        Integer page,
        Integer size,
        String query,
        OrderRequest order
) {

    public record OrderRequest(
            String field,
            String direction
    ) { }

}
