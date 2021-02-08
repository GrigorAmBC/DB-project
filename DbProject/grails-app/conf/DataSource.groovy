import org.hibernate.dialect.PostgreSQL10Dialect

dataSource {
    dbCreate: none
    dialect = PostgreSQL10Dialect
    driverClassName = 'org.postgresql.Driver'
    password = 'postgres'
    url = 'jdbc:postgresql://localhost/db_project'
    username = 'postgres'
}