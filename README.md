API REST de Reservas de Aulas

Sistema de gestión de reservas de aulas para centros educativos desarrollado con Spring Boot 3.5.6.


Estructura del Proyecto
com.example.ProyectoReservas/
├── controller/     # Endpoints REST
├── dto/            # Data Transfer Objects
├── requests/       # Request DTOs
├── entities/       # Entidades JPA
├── repositories/   # Repositorios JPA
├── services/       # Lógica de negocio
├── security/       # Configuración JWT y Security
├── exception/      # Manejo global de excepciones


Configuración Base de Datos

MySQL URL: jdbc:mysql://localhost:3306/ProyectoReservas

Usuario: alumno

Password: alumno1

H2 URL (opcional): jdbc:h2:mem:testdb


Usuarios por defecto

Rol	Email	Password
Admin	admin@example.com
	admin123
Profesor	profesor@example.com
	profesor123
Endpoints
Autenticación (Público)
Método	Endpoint	Descripción	Rol requerido
POST	/auth/register	Registrar usuario	Ninguno
POST	/auth/login	Iniciar sesión	Ninguno
GET	/auth/perfil	Obtener perfil	Autenticado

Ejemplo Register:

{
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "password": "password123",
  "role": "ROLE_PROFESOR"
}


Ejemplo Login:

{
  "email": "admin@example.com",
  "password": "admin123"
}

Usuarios
Método	Endpoint	Descripción	Rol requerido

DELETE	/usuario/{id}	Eliminar usuario	Autenticado
PUT	/usuario/{id}	Actualizar usuario	Autenticado
PATCH	/usuario/cambiar-pass	Cambiar contraseña	Autenticado

Ejemplo PUT /usuario/{id}:

{
  "nombre": "Juan Pérez Actualizado",
  "email": "juan.nuevo@example.com"
}


Ejemplo PATCH /usuario/cambiar-pass:

{
  "currentPassword": "password123",
  "newPassword": "newpassword456"
}

Aulas
Método	Endpoint	Descripción	Rol requerido
GET	/aulas	Listar todas las aulas	ADMIN, PROFESOR
GET	/aulas?capacidad=20	Aulas con capacidad > 20	ADMIN, PROFESOR
GET	/aulas?ordenadores=true	Aulas con ordenadores	ADMIN, PROFESOR
GET	/aulas/{id}	Obtener aula	ADMIN, PROFESOR
POST	/aulas	Crear aula	ADMIN, PROFESOR
PUT	/aulas/{id}	Modificar aula	ADMIN, PROFESOR
DELETE	/aulas/{id}	Eliminar aula	ADMIN, PROFESOR
GET	/aulas/{id}/reservas	Reservas de un aula	ADMIN, PROFESOR

Ejemplo POST /aulas:

{
  "nombre": "Aula 102",
  "capacidad": 30,
  "esAulaDeOrdenadores": false,
  "numeroOrdenadores": 0
}

Reservas
Método	Endpoint	Descripción	Rol requerido
GET	/reservas	Listar reservas	ADMIN, PROFESOR
GET	/reservas/{id}	Obtener reserva	ADMIN, PROFESOR
POST	/reservas	Crear reserva	ADMIN, PROFESOR
DELETE	/reservas/{id}	Eliminar reserva	ADMIN, PROFESOR*

*Profesores solo pueden eliminar sus propias reservas, los admin pueden eliminar cualquiera.

Ejemplo POST /reservas:

{
  "fecha": "2025-12-01",
  "motivo": "Clase de matemáticas",
  "numeroAsistentes": 25,
  "aulaId": 1,
  "tramoHorarioId": 1
}

Tramos Horarios
Método	Endpoint	Descripción	Rol requerido
GET	/tramo-horario	Listar tramos	ADMIN
GET	/tramo-horario/{id}	Obtener tramo	ADMIN
POST	/tramo-horario	Crear tramo	ADMIN
DELETE	/tramo-horario/{id}	Eliminar tramo	ADMIN

Ejemplo POST /tramo-horario:

{
  "diaSemana": "LUNES",
  "sesionDia": 1,
  "horaInicio": "08:00:00",
  "horaFin": "09:00:00",
  "tipo": "LECTIVA"
}

DTOs

Request DTOs

AulaRequest → nombre, capacidad, esAulaDeOrdenadores, numeroOrdenadores

ReservaRequest → fecha, motivo, numeroAsistentes, aulaId, tramoHorarioId

TramoHorarioRequest → diaSemana, sesionDia, horaInicio, horaFin, tipo

RegisterRequest → nombre, email, password, role

LoginRequest → email, password

UpdateUsuarioRequest → nombre, email

ChangePasswordRequest → currentPassword, newPassword

Response DTOs

AulaDTO → id, nombre, capacidad, esAulaDeOrdenadores, numeroOrdenadores

ReservaDTO → id, fecha, motivo, numeroAsistentes, fechaCreacion, aula, tramoHorario, usuarioNombre

TramoHorarioDTO → id, diaSemana, sesionDia, horaInicio, horaFin, tipo

UsuarioDTO → id, nombre, email, role

LoginResponse → token, nombre, email, role

ErrorResponse → mensaje, status, timestamp

Validaciones de Negocio

✅ No permitir reservas solapadas (mismo aula, fecha y tramo horario)
✅ No permitir reservas en fechas pasadas
✅ Número de asistentes no puede superar capacidad del aula
✅ Profesores solo pueden eliminar sus propias reservas
✅ Admin tiene acceso total a todos los endpoints

Autenticación JWT

Todas las peticiones excepto /auth/register y /auth/login requieren un token JWT:

Authorization: Bearer {token}

Enums Disponibles

Role: ROLE_ADMIN, ROLE_PROFESOR
DiaSemana: LUNES, MARTES, MIERCOLES, JUEVES, VIERNES
TipoTramo: RECREO, LECTIVA, MEDIODIA
