# Sistema de Gestion de Inventarios - SISRA

Regatta es un almacén mediano ubicado en el sur de Cali donde se comercializan prendas masculinas y femeninas, cuenta con poco personal ya que apenas está en crecimiento sin embargo la producción es cada vez más estable y en aumento, las prendas son traídas de ciudades como Medellín y bucaramanga y otras desde Panamá, el enfoque está inclinado hacia  los jóvenes entre los 18 y 25 años y busca causar tendencia y estar actualizada en las diferentes modas de la actualidad y así impactar en sus clientes siendo uno de los locales preferidos por todos.

## Comenzando 🚀
Puedes obtener el proyecto de dos formas
```
  •  Mediante una terminar ejecutamos git clone https://github.com/anne1025/SISRA.git
  •  En la parte superior del proyecto “Download ZIP”
```

### Pre-requisitos 📋
•	Sistema operativo Windows, Linux  o MAC

•	Servidor de base de datos PostgreSQL 9.0.3 o superior

•	IDE NetBeans 8.1 o superior

•	Máquina virtual Java JDK 1.8 para compilar

### Instalación 🔧

1.	Debemos importar el proyecto Netbeans o cargar las clases en su IDE de preferencia

2.	En net beans debemos resolver las dependencias de las librerías dando clic en “Resolve Problems Project”

	![Alt text](/screenshots/resolveproblems_netbeans.png)
		
3.	Seleccionamos una a una las librerías de la carpeta “lib”
	
	![Alt text](/screenshots/listlibrary_netbeans.png)

4.	Por ultimo ejecutamos “Clean and Build”

	![Alt text](/screenshots/build_netbeans.png)

5.	Para cargar la base de datos podemos realizar un restore desde postgreSQL o ejecutar el script SQL ambas convenciones se encuentran en la carpeta “db” del proyecto

## Deployment 📦
1.	Para ingresar al Rol del admin las credenciales son admin:admin
2.	Verificar que el nombre de la base de datos que cargamos sea igual en el gestor y en la fachada.
3.	El ejecutable de la aplicacion se encuentra en la carpeta exe

## Construido con 🛠️

* [Netbeans](https://netbeans.org/) - IDE
* [Java Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) – Biblioteca Grafica
* [PostgreSQL](https://www.postgresql.org/) – Gestor de BD

## Contribuyendo 🖇️
Por favor lee el [CONTRIBUTING.md](https://gist.github.com/anne1025/SISRA) para detalles de nuestro código de conducta, y el proceso para enviarnos pull requests.

## Versionado 📌
Usamos [Git](https://git-scm.com/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/anne1025/SISRA/tags).

## Autores ✒️

* **Ana Pereira** - *Analisis y Desarrollo* - [anne1025](https://github.com/anne1025)

También puedes mirar la lista de todos los [contribuyentes](https://github.com/anne1025/SISRA/contributors) quíenes han participado en este proyecto. 

## Licencia 📄

Este proyecto está bajo la Licencia GNU General Public License 3.0 - mira el archivo [LICENSE.md](LICENSE.md) para detalles
