package com.appJava.project6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appJava.project6.entity.Transfer;

@Repository
public interface TransfertRepository extends JpaRepository<Transfer, Long> {

	List<Transfer> findByBankAccountAccountId(Long accountId);

	List<Transfer> findByUserUserId(Long userId);

}
