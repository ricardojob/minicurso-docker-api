## Criar o arquivo `Dockerfile` do banco PostgreSQL
```
FROM postgres
ENV POSTGRES_DB clientes
ENV POSTGRES_USER job
ENV POSTGRES_PASSWORD 123
COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/
```
## Criar uma imagem do banco PostgreSQL
`docker build -t ricardojob/banco ./postgres`:  
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`./postgres`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  


## Criar o arquivo `Dockerfile` da aplicação
```
FROM payara/server-web
COPY target/app.war $DEPLOY_DIR
```

## Criar uma imagem da aplicação

`docker build -t ricardojob/app .`:  
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`.`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  

## Executar o container  

`docker run -p 5433:5432 --name bd -d ricardojob/bd` e 
`docker run -p 8082:8080 --name app --link bd:host-banco ricardojob/app`:   
*`-p`: o bind entre a porta do host local com a porta do container*  
*`-d`: o container seja executar em background*  
*`--link`: o bind entre os containers, para pertimir que o container da aplicação tenha acesso ao container do banco*  
*`--name`: o nome do container*  


## Acesso à aplicação

*`http://localhost:8082/app`: formulário para cadastro dos `clientes` e `produtos`*  
*`http://localhost:8082/app/clientes`: listando os clientes (usando JDBC)*  
*`http://localhost:8082/app/produtos`: listando os produtos (usando JPA)*  
