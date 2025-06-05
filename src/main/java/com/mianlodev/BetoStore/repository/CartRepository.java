package com.mianlodev.BetoStore.repository;

import com.mianlodev.BetoStore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProducto_Id(Long productId);
    void deleteByProducto_Id(Long productId);


}