package com.rodrigoc.YovoyAPI.Controller;

import com.rodrigoc.YovoyAPI.Domain.Tarjeta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @PostMapping
    public ResponseEntity<DatosListadoT> registrarTarjeta(@RequestBody @Valid DatosTarjeta datosTarjeta, UriComponentsBuilder uriComponentsBuilder){

        Tarjeta tarjeta = tarjetaRepository.save(new Tarjeta(datosTarjeta));
        return ResponseEntity.ok(new DatosListadoT(tarjeta.getFolio(),tarjeta.getTipo(), tarjeta.getMonto()));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoT>> listadoTarjetas(@PageableDefault(size = 10)Pageable paginacion){
        return ResponseEntity.ok(tarjetaRepository.findByActivoTrue(paginacion).map(DatosListadoT::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTarjeta(@RequestBody @Valid DatosActualizarT datosActualizarT){
        Tarjeta tarjeta = tarjetaRepository.getReferenceById(datosActualizarT.folio());
        tarjeta.actualizarTarjeta(datosActualizarT);
        return ResponseEntity.ok(new DatosListadoT(tarjeta.getFolio(),tarjeta.getTipo(), tarjeta.getMonto()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTarjeta(@PathVariable Long id){
        Tarjeta tarjeta = tarjetaRepository.getReferenceById(id);
        tarjeta.desactivarTarjeta();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoT> retornarTarjeta(@PathVariable Long id){
        Tarjeta tarjeta = tarjetaRepository.getReferenceById(id);
        var datosTarjeta = new DatosListadoT(tarjeta.getFolio(), tarjeta.getTipo(), tarjeta.getMonto());
        return ResponseEntity.ok(datosTarjeta);
    }

}
