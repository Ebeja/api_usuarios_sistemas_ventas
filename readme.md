DOCUMENTACION HASTA EL MOMENTO
login:
URL; http://localhost:8081/api/usuarios/login
METODO: POST

json:
{
"usuario": "TEST20",
"clave" : "123456"
}

obtener administradores:
URL: http://localhost:8081/api/usuarios/administradores
METODO: GET

obtener vendedores
URL: http://localhost:8081/api/usuarios/vendedores
METODO:GET

Editar campos parciales

URL:http://localhost:8081/api/usuarios/vendedores/ID(remplazar por el id que corresponde)
METODO: PATCH
JSON:    {
"nombre": "CR7",
"apellido": "RONALDO",
"dni": "63636363",
"usuario": "CR7",
"claveCifrada": "123456",
"estado": 1
}