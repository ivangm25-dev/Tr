# 🚀 API de Gestión de Órdenes y Productos

Sistema backend desarrollado con **Spring Boot**, gestionando precisión financiera con `BigDecimal`, seguridad vía **JWT** y monitoreo de rendimiento con **AOP**.

## Autenticación (Login)

Para consumir los endpoints de la API (excepto el login), es necesario estar autenticado.

* **Endpoint:** `POST /api/v1/login`
* **Credenciales de acceso:**
    * **Usuario:** `admin`
    * **Password:** `admin123`

Al hacer login, recibirás un `Token`. Debes incluirlo en el Header de cada petición:
`Authorization: Bearer <TU_TOKEN_AQUI>`

---

## Gestión de Órdenes (`/api/v1/orden`)

### 1. Consultar Órdenes (GET)
Obtiene el listado de todas las órdenes y sus productos.
* **Método:** `GET`
* **Auth:** Requerido (JWT)

### 2. Crear Orden (POST)
Registra una nueva orden. El sistema calcula automáticamente el total basado en el precio de los productos.

* **Método:** `POST`
* **Estructura del Body:**
```json
{
    "sucursal": "MX",
    "productos": [
        {
            "codigo": "111",
            "descripcion": "taza decorada",
            "precio": 10.00
        }
    ]
}
```

### 3. Actualizae Orden (PUT)
Actualiza la sucursal y productos en base a un id de orden.

* **Método:** `PUT`
* **Estructura del Body:**
```json
{
    "ordenId": 1,
    "sucursal": "MX",
    "productos": [
        {
            "codigo": "111",
            "descripcion": "taza decorada",
            "precio": 10.00
        }
    ]
}
```

