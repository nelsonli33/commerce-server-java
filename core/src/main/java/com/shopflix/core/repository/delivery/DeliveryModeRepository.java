package com.shopflix.core.repository.delivery;

import com.shopflix.core.model.order.delivery.DeliveryModeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryModeRepository extends JpaRepository<DeliveryModeModel, Long>
{
}
