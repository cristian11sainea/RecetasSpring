#!/bin/bash

# Script para probar la API de productos

# URL base de la API
BASE_URL="http://localhost:8080/api/recetas"

# 1. Crear un nuevo producto
echo "Creando un nueva receta..."
curl -v -X POST $BASE_URL \
-H "Content-Type: application/json" \
-d '{"nombre": "Receta 1"}'
echo -e "\n"


echo "Creando un nuevo receta..."
curl -X POST $BASE_URL \
-H "Content-Type: application/json" \
-d '{"nombre": "Receta X"}'
echo -e "\n"


echo "Creando un nuevo Receta..."
curl -X POST $BASE_URL \
-H "Content-Type: application/json" \
-d '{
  "nombre": "RecetaEjemplo",
  "duracion": 30
}'
echo -e "\n"

# 2. Obtener todos los productos
echo "Obteniendo todos los recetas..."
curl -X GET $BASE_URL
echo -e "\n"

# 3. Actualizar el producto con ID 1
echo "Actualizando el Receta con ID 1..."
curl -X PUT $BASE_URL/1 \
-H "Content-Type: application/json" \
-d '{"nombre": "Receta Actualizado"}'
echo -e "\n"

# 4. Obtener todos los productos después de la actualización
echo "Obteniendo todos los recetas después de la actualización..."
curl -X GET $BASE_URL
echo -e "\n"

# 5. Eliminar el producto con ID 1
echo "Eliminando el receta con ID 1..."
curl -X DELETE $BASE_URL/3
echo -e "\n"

# 6. Obtener todos los productos después de la eliminación
echo "Obteniendo todos los recetas después de la eliminación..."
curl -X GET $BASE_URL
echo -e "\n"

echo "Pruebas completadas."
