package com.mianlodev.BetoStore.repository;

import com.mianlodev.BetoStore.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByEndDateAfterAndStartDateBefore(LocalDate endDate, LocalDate startDate);
}