package org.dormhub.www.controller.rest.mapper;

import org.dormhub.www.controller.rest.dto.rq.SearchRequest;
import org.dormhub.www.controller.rest.dto.rs.IdResponse;
import org.dormhub.www.service.dto.SearchDto;

import java.util.UUID;

public interface CommonWebMapper<T> {

    IdResponse toResponse(UUID id);

    SearchDto toDto(SearchRequest request);

}
