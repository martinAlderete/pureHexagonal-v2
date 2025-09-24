package com.gyl.bys.domain.VO;


public enum EstadoUsuario {
    PENDIENTE, // Nuevo usuario, esperando aprobación
    ACTIVO,    // Usuario aprobado, puede usar el sistema
    INACTIVO,  // Usuario que fue dado de baja
    RECHAZADO  // Usuario que fue rechazado en la aprobación
}