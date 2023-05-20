#!/bin/bash

# Definir función para calcular la serie de Fibonacci
fibonacci() {
    n=$1
    if ((n <= 0)); then
        echo 0
    elif ((n == 1)); then
        echo 1
    else
        a=0
        b=1
        for ((i=2; i<=n; i++)); do
            fn=$((a + b))
            a=$b
            b=$fn
        done
        echo $fn
    fi
}

# Puerto en el que el servidor escucha las conexiones
puerto=9099

# Iniciar el servidor
echo "Iniciando el servidor..."

# Ciclo infinito para recibir conexiones
while true; do
    # Esperar a que un cliente se conecte
    mensaje=$(nc -l $puerto)
    if [[ $mensaje ]]; then
        # El cliente envió un mensaje válido
        echo "Cliente conectado. Mensaje recibido: $mensaje"

        # Validar y calcular la serie de Fibonacci
        if [[ $mensaje =~ ^[0-9]+$ ]]; then
            resultado=$(fibonacci $mensaje)
            echo "Resultado: $resultado"
        else
            echo "Mensaje inválido. Se esperaba un número entero."
        fi
    fi
done