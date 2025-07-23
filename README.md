# Gestor de Recursos Educativos

El objetivo de este proyecto es aplicar los conocimientos adquiridos sobre el uso de Git como sistema de control de versiones para el desarrollo colaborativo de una solución de software. Se busca demostrar la gestión eficiente del código fuente, la colaboración en equipo y la trazabilidad del desarrollo a través del uso de ramas, commits, Pull Requests, merges, resolución de conflictos y releases.

Se utilizaron dos repositorios diferentes para llevar a cabo las actividades. Para contribuir, siga los siguientes pasos:

## ⚙️ Backend

1. **Hacer un fork y clonar este repositorio**
   ```bash
   git clone https://github.com/Herramientas-de-Desarrollo/sistema-gestion-recursos-educativos.git
   ```

2. **Abrirlo desde tu IDE preferido**, en nuestro caso recomendamos IntelliJ IDEA

3. **Configurar la base de datos**
   - Debes crear una base de datos llamada "sgr" en MySQL para poder usar el sistema
   - Ten en cuenta los datos que se encuentran en el archivo `application.properties`

4. **Ejecutar la aplicación**
   - Abre http://localhost:8080 para usar la API correspondiente y verás los resultados 🚀

## 💻 Frontend

1. **Hacer un fork y clonar el repositorio**
   ```bash
   git clone https://github.com/Herramientas-de-Desarrollo/frontend-gestion-recursos-educativos.git
   ```

2. **Instalar las dependencias**
   - Nosotros usamos pnpm para manejar las dependencias

   ```bash
   # Instalar pnpm de manera global si no lo tienes:
   npm install -g pnpm

   # Instalar dependencias:
   pnpm install
   ```

3. **Correr el servidor de desarrollo**
   ```bash
   # Con pnpm:
   pnpm dev
   ```

## 🤝 Contribución

Para contribuir al proyecto, asegúrate de seguir las mejores prácticas de Git:
- Crear ramas específicas para cada funcionalidad
- Realizar commits descriptivos
- Crear Pull Requests detallados
- Resolver conflictos de manera adecuada
- Participar en el proceso de code review

## 📋 Requisitos

### Backend
- Java (versión recomendada según el proyecto)
- MySQL
- IntelliJ IDEA (recomendado)

### Frontend
- Node.js
- pnpm (gestor de paquetes)
- Navegador web moderno
