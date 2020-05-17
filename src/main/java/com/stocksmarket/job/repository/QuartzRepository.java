package com.stocksmarket.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocksmarket.job.model.entity.QrtzFiredTriggers;

@Repository
public interface QuartzRepository extends JpaRepository<QrtzFiredTriggers, String> {
}
