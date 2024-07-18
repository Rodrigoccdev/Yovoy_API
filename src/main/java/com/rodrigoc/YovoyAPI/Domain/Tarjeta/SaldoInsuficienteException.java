package com.rodrigoc.YovoyAPI.Domain.Tarjeta;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
