package com.skillstorm.warehouses.repositories;


import com.skillstorm.warehouses.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

// JPARepository will give us build in methods as findAll and findById
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    // query to update currentstock in postgresSQL
    @Query("UPDATE Warehouse SET currentStock = currentStock + :curntStk WHERE id = :wId")
    @Modifying
    @Transactional
    int updateAvailable(@Param("curntStk") int curntStk, @Param("wId") int wId);

    @Query("UPDATE Warehouse SET currentStock = currentStock - :curntStk WHERE id = :wId")
    @Modifying
    @Transactional
    int deleteAvailable(@Param("curntStk") int curntStk, @Param("wId") int wId);

}
