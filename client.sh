#!/bin/bash

# Dirección IP y puerto del servidor
ip_servidor="10.147.19.26" #hgrid4
puerto_servidor=9099

# Número de clientes que enviarán mensajes
num_clientes=10

# Número grande para calcular la serie de Fibonacci
numero_grande=100000

# Enviar mensajes al servidor desde múltiples clientes
for ((i=1; i<=num_clientes; i++)); do
    echo "Enviando mensaje desde el cliente $i..."
    echo $numero_grande | nc $ip_servidor $puerto_servidor &
done