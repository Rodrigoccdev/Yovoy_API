package com.rodrigoc.YovoyAPI.Domain.Tarjeta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "tarjetas")
@Entity(name = "Tarjeta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folio;
    private String tipo;
    private Double monto;
    private boolean activo;

    public Tarjeta(DatosTarjeta datosTarjeta){
        this.tipo = datosTarjeta.tipo();
        this.monto = 10.5;
        this.activo = true;
    }

    public void actualizarTarjeta(DatosActualizarT datosActualizarT){
        try{
            if (datosActualizarT.monto() != null){
                if(datosActualizarT.operacion().equalsIgnoreCase("Recarga"))
                    this.monto += datosActualizarT.monto();
            }
            else if (datosActualizarT.operacion().equalsIgnoreCase("Cobro")) {
                double nuevoMonto = this.monto;
                if (this.tipo.equalsIgnoreCase("Ordinaria")) {
                    nuevoMonto -= 10.5;
                } else if (this.tipo.equalsIgnoreCase("Estudiante")) {
                    nuevoMonto -= 5.25;
                }
                if (nuevoMonto < 0) {
                    throw new SaldoInsuficienteException("Saldo insuficiente para realizar la operación de cobro.");
                }
                this.monto = nuevoMonto;
            }
        }catch (SaldoInsuficienteException e){
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Long getFolio() {
        return folio;
    }

    public void setFolio(Long folio) {
        this.folio = folio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void desactivarTarjeta(){
        this.activo = false;
    }
}
