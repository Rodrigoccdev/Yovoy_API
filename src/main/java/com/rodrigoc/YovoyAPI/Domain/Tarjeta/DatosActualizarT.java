package com.rodrigoc.YovoyAPI.Domain.Tarjeta;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarT(@NotNull Long folio, Double monto, String operacion){
}
