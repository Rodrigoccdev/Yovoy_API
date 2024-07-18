package com.rodrigoc.YovoyAPI.Domain.Tarjeta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Page<Tarjeta> findByActivoTrue(Pageable paginacion);
}
