package Inventario.model;

public enum Estado {
    ACTIVO(0), INACTIVO(1);
    private int value;

    Estado(int value) {
        this.value = value;
    }
}
