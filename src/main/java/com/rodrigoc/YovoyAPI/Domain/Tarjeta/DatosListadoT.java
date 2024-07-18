package com.rodrigoc.YovoyAPI.Domain.Tarjeta;

public record DatosListadoT(Long folio, String tipo, Double monto) {
    public DatosListadoT(Tarjeta tarjeta){
        this(tarjeta.getFolio(), tarjeta.getTipo(), tarjeta.getMonto());
    }
}
