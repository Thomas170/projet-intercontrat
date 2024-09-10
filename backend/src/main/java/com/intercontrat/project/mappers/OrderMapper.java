package com.intercontrat.project.mappers;

import com.intercontrat.project.dtos.OrderDTO;
import com.intercontrat.project.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderMapper {
    OrderDTO toOrderDTO(Order order);
    Order toOrder(OrderDTO orderDTO);
}
