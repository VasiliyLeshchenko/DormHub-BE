package org.dormhub.www.util;

import org.dormhub.www.service.dto.SearchDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public final class PageUtils {

    public static Order order(SearchDto.OrderDto orderDto) {
        Direction direction = Direction.fromString(orderDto.direction());
        return new Order(direction, orderDto.field());
    }

    public static Sort singleFieldSort(SearchDto.OrderDto orderDto) {
        return Sort.by(PageUtils.order(orderDto));
    }

}
