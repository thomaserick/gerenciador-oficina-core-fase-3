package com.fiap.pj.core.vehicle.exception;

public class VehicleExceptions {
    private VehicleExceptions() {
    }

    public static class VehicleNotFoundException extends RuntimeException {
        public VehicleNotFoundException() {
            super("Veículo não encontrado.");
        }
    }

    public static class VehiclePlateDuplicateException extends RuntimeException {
        public VehiclePlateDuplicateException() {
            super("Placa do veículo já cadastrada.");
        }
    }
}
