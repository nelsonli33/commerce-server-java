package com.shopflix.core.repository.delivery;

import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryModeRepository extends JpaRepository<DeliveryModeModel, Long>
{
    List<DeliveryModeModel> findDeliveryModeModelsByActiveTrue();

    DeliveryModeModel findDeliveryModeModelByCode(String code);
}
