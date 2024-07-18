package com.rodrigoc.YovoyAPI.Domain.Tarjeta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTarjeta(@NotBlank String tipo, Double monto) {
}
