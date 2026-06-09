WildFly Deployment Instructions

1) Java compatibility
- Build the project with Java 17. WildFly releases may not support newer Java versions.

2) Configure the datasource on WildFly
- Create a datasource named `ExampleDS` that points to the `examsdb` MySQL database.
- Example CLI commands (adjust username/password/hostname/port as needed):

  /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
  data-source add --name=ExampleDS --driver-name=mysql --jndi-name=java:/jboss/datasources/ExampleDS --connection-url=jdbc:mysql://localhost:3306/examsdb?useSSL=false\u0026serverTimezone=UTC --user-name=root --password=password

- Or use the WildFly admin console (`http://<host>:9990`) -> Configuration -> Subsystems -> Datasources -> Add.

3) Build and deploy
- Build the WAR: `mvn clean package` (ensure Maven uses JDK 17)
- Deploy by copying `target/project_3.war` into WildFly `standalone/deployments/`, or use the Management Console/CLI.

4) Notes
- The project uses the provided Jakarta EE API, so application server should provide Jakarta EE implementation.
- If migrating to a different WildFly version, ensure Jakarta EE 10 compatibility or adjust `jakarta.jakartaee-api` version.

