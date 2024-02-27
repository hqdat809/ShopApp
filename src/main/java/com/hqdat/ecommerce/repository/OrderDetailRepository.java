package com.hqdat.ecommerce.repository;

import com.hqdat.ecommerce.model.Order;
import com.hqdat.ecommerce.model.OrderDetail;
import com.hqdat.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<List<OrderDetail>> findByUser(User user);
    Optional<List<OrderDetail>> findByOrder(Order order);
}
