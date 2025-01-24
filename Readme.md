
# Ecommerce Spring Boot Batch

### Link para java
- https://github.com/carm-es/guias/blob/master/java/Buenas-Practicas-de-codificacion.md#organizaci%C3%B3n-del-c%C3%B3digo
- https://github.com/carm-es/guias/blob/master/java/Buenas-Practicas-de-codificacion.md
- https://jenkov.com/tutorials/java-exception-handling/index.html
- 

## Descripción
Este proyecto es una aplicación de Spring Boot que procesa archivos CSV para insertar productos y pedidos en la base de datos utilizando Spring Batch.

## Link de Documentación

- https://docs.spring.io/spring-boot/how-to/batch.html#howto.batch.running-jobs-on-startup
- https://docs.spring.io/spring-batch/reference/spring-batch-architecture.html

## Tecnologías utilizadas
- Spring Boot
- Spring Batch
- Spring Security
- MySQL
- Prometheus
- Grafana

## Configuración
1. Clona el repositorio.
2. Configura la base de datos en `application.properties`.
3. Ejecuta la aplicación con:
   ```bash
   mvn spring-boot:run

### Link de Consultas
 - Prometheus: http://localhost:9090/query?g0.expr=&g0.show_tree=0&g0.tab=table&g0.range_input=1h&g0.res_type=auto&g0.res_density=medium&g0.display_mode=lines&g0.show_exemplars=0
 - Grafana: http://localhost:3000/connections/datasources/edit/ee9kmv6lhhvr4d.
 - Reiniciar Prometheus: posicionarme donde se encuentra prometheus.exe "C:\prometheus" y ejecutar el comando: .\prometheus.exe --config.file=prometheus.yml
 - Reiniciar grafana: ir a ventana de servicios de windows "services.msc", buscar grafan y reiniciar.
 - Elasticsearch: http://localhost:9200/. Ir a la ruta:  C:\elasticsearch, ejecutar el comando: bin\elasticsearch.bat
 - Kibana: http://localhost:5601/ y http://localhost:5601/app/home#/ - C:\kibana\kibana; ejecutar el comando: bin\kibana.bat


### Métricas para monitorear en Grafana
<table style="width: 100%; text-align: center;">
  <tr>
    <td style="width: 50%;">Métrica</td>
    <td style="width: 50%;">Descripción</td>
  </tr>
  <tr>
    <td style="width: 20%; padding-top: 3px;">http_server_requests_seconds_count</td>
    <td style="width: 20%; padding-top: 3px;">Cantidad de solicitudes HTTP que recibe tu aplicación.</td>
  </tr>
  <tr>
    <td style="width: 50%; padding-top: 3px;">jvm_memory_used_bytes</td>
    <td style="width: 50%; padding-top: 3px;">Cantidad de memoria utilizada por la JVM</td>
  </tr>
  <tr>
    <td style="width: 50%; padding-top: 3px;">process_cpu_usage</td>
    <td style="width: 50%; padding-top: 3px;">Uso de CPU del proceso de tu aplicación.</td>
  </tr>
  <tr>
    <td style="width: 50%; padding-top: 3px;">system_cpu_usage</td>
    <td style="width: 50%; padding-top: 3px;">Uso total de CPU del sistema donde corre tu aplicación.</td>
  </tr>
</table>

