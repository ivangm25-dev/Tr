package com.wigm.tr.repository;

import com.wigm.tr.entities.repository.Orden;
import com.wigm.tr.entities.repository.Producto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Producto p WHERE p.orden.id = :ordenId")
    void deleteByOrdenId(@Param("ordenId") Long ordenId);
    List<Producto> findByOrden(Orden orden);
}
