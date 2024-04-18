# Flujo de la Aplicación y Componentes Utilizados

## Inicio de la App (Bienvenida)

- **Activity**: `WelcomeActivity.kt`
- **Layout**: `activity_welcome.xml`
- Descripción: Contiene botones para "Iniciar sesión" y "Registrarse". No necesito un ViewModel aquí porque normalmente no hay una lógica de negocio asociada con una pantalla de bienvenida estática.

## Registro de Usuarios

- **Activity**: `RegisterActivity.kt`
- **Layout**: `activity_register.xml`
- **ViewModel**: `UserViewModel.kt`, manejo la creación de cuentas y la autenticación.
- **Services**: `AuthenticationService.kt`, utilizado por el ViewModel para interactuar con Firebase Authentication.
- **Repository**: `UserRepository.kt`, abstraigo las operaciones de Firebase y proveo datos al ViewModel.

## Inicio de Sesión de Usuarios

- **Activity**: `LoginActivity.kt`
- **Layout**: `activity_login.xml`
- **ViewModel**: Comparto el `UserViewModel.kt` con el registro.
- **Services & Repository**: Los mismos que uso para el registro.

## Personalización del Perfil

- **Activity**: `ProfileCustomizationActivity.kt`
- **Layout**: `activity_profile_customization.xml`
- **ViewModel**: `ProfileViewModel.kt`, manejo la actualización de la información del perfil en Firestore.
- **Repository**: `UserRepository.kt`, interactúo con Firestore para almacenar los datos del perfil.

## Vista Principal (Home) con Categorías de Ejercicios

- **Activity**: `MainActivity.kt`
- **Layout**: `activity_main.xml`
- **Fragment**: `HomeFragment.kt`
- **Layout de Fragment**: `fragment_home.xml`, muestro categorías de ejercicios en una lista o cuadrícula.
- **ViewModel**: `HomeViewModel.kt`, cargo las categorías de Firestore y las expongo al fragmento.
- **Adapters**: `CategoryAdapter.kt`, para inflar vistas en el RecyclerView del fragmento.
- **Repository**: `CategoryRepository.kt`, gestiono la recuperación de categorías de ejercicios de Firestore.

## Lista de Ejercicios por Categoría

- **Fragment**: `ExerciseListFragment.kt`
- **Layout de Fragment**: `fragment_exercise_list.xml`
- **ViewModel**: `ExerciseViewModel.kt`, encargado de obtener los ejercicios de una categoría específica.
- **Adapters**: `ExerciseAdapter.kt`, muestro ejercicios en el RecyclerView.
- **Repository**: `ExerciseRepository.kt`, obtengo los datos de ejercicios de Firestore.

## Detalle del Ejercicio Seleccionado

- **Fragment**: `ExerciseDetailFragment.kt`
- **Layout de Fragment**: `fragment_exercise_detail.xml`, muestro la descripción del ejercicio, las repeticiones y un video tutorial.
- **ViewModel**: Puedo reutilizar `ExerciseViewModel.kt` para detalles específicos.
- **Models**: `Exercise.kt`, estructura de datos para un ejercicio específico.

## Perfil de Usuario

- **Fragment**: `ProfileFragment.kt`
- **Layout de Fragment**: `fragment_profile.xml`, muestro información de perfil y seguimiento de peso.
- **ViewModel**: `ProfileViewModel.kt`, manejo la lógica para mostrar y actualizar la información del perfil.

## Utilities

- Contengo archivos como `Constants.kt` y `Utils.kt` que incluyen constantes comunes y funciones de ayuda respectivamente, usadas a través de la app.

## Services y Repositories

- **Services**: Como `AuthenticationService.kt`, que maneja la comunicación con Firebase Authentication.
- **Repositories**: Como `UserRepository.kt`, `ExerciseRepository.kt`, y `CategoryRepository.kt`, que abstraen las llamadas a la base de datos y cualquier lógica de procesamiento de datos.

## Conexión entre Componentes

- **Activities y Fragments**: Las actividades actúan como contenedores para los fragments. Por ejemplo, `MainActivity` aloja `HomeFragment`, `ExerciseListFragment`, y `ProfileFragment` y gestiona la navegación entre ellos.
- **ViewModels**: Cada ViewModel está conectado con su Activity o Fragment correspondiente y maneja la lógica de negocio asociada. Se comunica con uno o más Repositories para obtener y enviar datos.
- **Adapters**: Los adapters se usan dentro de los Fragments para poblar listas y cuadrículas con datos, conectando los datos del ViewModel a las vistas RecyclerView.
- **Models**: Los modelos representan las estructuras de datos y son utilizados por los ViewModels y Repositories.
- **Utilities**: Las funciones de utilidad son invocadas donde sea necesario, por ejemplo, para formatear fechas o validar entradas.
- **Services y Repositories**: Los Services definen las operaciones concretas para interactuar con Firebase y otras APIs. Los Repositories utilizan estos Services para obtener datos y luego los pasan a los ViewModels.
