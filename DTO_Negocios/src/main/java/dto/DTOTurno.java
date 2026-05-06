/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.awt.Color;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author jesus
 */
public class DTOTurno {

    private String idTurno;
    private String nombre;
    
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Set<DayOfWeek> diasTrabajo;

    private Color colorEvento;
    
    private String colorHexadecimal;

    public DTOTurno() {
    }

    public DTOTurno(String idTurno, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo) {
        this.idTurno = idTurno;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
    }

    public DTOTurno(String idTurno, String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo, Color colorEvento) {
        this.idTurno = idTurno;
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorEvento = colorEvento;
    }

    public DTOTurno(String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
    }

    public DTOTurno(String nombre, LocalTime horaInicio, LocalTime horaFin, Set<DayOfWeek> diasTrabajo, Color colorEvento) {
        this.nombre = nombre;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.diasTrabajo = diasTrabajo;
        this.colorEvento = colorEvento;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public Set<DayOfWeek> getDiasTrabajo() {
        return diasTrabajo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setDiasTrabajo(Set<DayOfWeek> diasTrabajo) {
        this.diasTrabajo = diasTrabajo;
    }

    public Color getColorEvento() {
        return colorEvento;
    }
    
    public String getColorHexadecimal(){
        return colorHexadecimal;
    }

    public void setColorEvento(Color colorEvento) {
        this.colorEvento = colorEvento;
        
        this.colorHexadecimal = String.format("#%02x%02x%02x", colorEvento.getRed(), colorEvento.getGreen(), colorEvento.getBlue());
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }


  


    
    
    
}
