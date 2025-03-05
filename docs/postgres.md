# Configurar o postgres para testar o projeto

## 1 Baixar o postrgres
### Docker
>❗ Baixar o docker antes de tudo  

Baixar uma imagem do postgres
```
docker pull postgres
```

Rodar a imagem
```
docker run --name postgres1 -e POSTGRES_PASSWORD=1234 -d -p 5432:5432 postgres
```
Depois disso é só conectar usando os dados usados:  
server: `localhost:5432/postgres`   
Usuario: `postgres`     
Senha: `1234`       

## 2 Entrar no postgres usando o docker
### Entrar no docker
```
docker exec -it postgres1 bash
```

### Acessar o postgres
```
psql -U postgres
```

## 3 Configurar o postgres
Após conectado e tendo acesso ao servidor sql   
### Criar a database `saluscontrolis`
```sql
CREATE DATABASE saluscontrolis;
```

### Ou apenas mudar o nome no config do java para outra database existente