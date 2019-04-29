# LOCADORA REST API

Este projeto foi construído em cima do ecossistema do Spring, utilizando o Spring boot como template inicial do projeto


## Pre-requisitos - Estar usando S.O Linux
 
É necessário possuir o docker instalado em sua máquina. E para instalar é só executar o seguite comando: 
````
curl -fsSL https://get.docker.com -o get-docker.sh && sh
````
e executa o comando: 
````
docker --version
````
Irá aparecer a versão do docker instalado em sua máquina

E, caso você queira executar esse projeto com o profile DEV, você deve instalar o docker-compose: 
````
sudo curl -L https://github.com/docker/compose/releases/download/1.18.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
````
.. e para dar permissao ao binário:
````
sudo chmod +x /usr/local/bin/docker-compose
````
Checa a sua instalação: 
````
docker-compose --version
````

### Agora que está tudo pronto para subir a aplicação... Vamos la!

Este projeto foi separado em dois tipos de perfis, irei dizer como subir o serviço dentro de um container fazendo com que o perfil desejado seja adotado ao projeto.

### Perfil Test

Este é o passo mais simples, basta executar o seguinte comando: 
````
docker run -p 8091:8080 -e "SPRING_PROFILES_ACTIVE=test" --name locadora-rest-api jonasjava/locadora-spring-boot
````
Explicando parâmetros: 
* -p -> É a porta onde o seu serviço irá operar, neste caso estou pedindo para liberar a porta 8091 do host e a 8080 do container.. izi pizi
* -e -> É a nossa variavel de ambiente que faz a nossa aplicação Spring identificar em qual profile ele deve funcionar
* --name -> Nome do nosso container..

E eras isso..

### Perfil Dev

Aqui não podemos usar o mesmo modo usado no passo anterior, pois o perfil dev funciona com um Banco de dados relacional, que neste caso é o Mysql.

Para subir a aplicação com o perfil dev devemos usar a tecnologia Docker Compose, uma grande e ótima feature do Docker.

Você pode baixar o arquivo docker-compose.yaml que encontra-se na raiz deste projeto, ou copiar o seguinte código e depois colar em seu proprio docker-compose.yaml
````$xslt
version: "3.5"
services:
  db:
    container_name: db_mysql
    image: mysql:5.6
    restart: always
    networks:
      - locadora-network
    ports:
      - 6603:3306
    environment:
      - MYSQL_ROOT_PASSWORD=rootpass
      - MYSQL_DATABASE=db_locadora
      - MYSQL_USER=locadora
      - MYSQL_PASSWORD=locadora

  locadora-rest-api:
    container_name: locadora-rest-api
    image: jonasjava/locadora-spring-boot
    restart: always
    depends_on:
      - db
    networks:
      - locadora-network
    ports:
      - 8091:8080
    environment:
      - DATABASE_HOST="db"
      - DATABASE_USER=locadora
      - DATABASE_NAME=db_locadora
      - DATABASE_PORT=6603
      - SPRING_PROFILES_ACTIVE=dev
    command: --spring.profiles.active=dev

networks:
  locadora-network:
```` 

### Ta e o que esse compose faz? 

### Vamos lá. 
* Primeiro eu dei a ordem de subir um serviço(container) utilizando o mysql:5.6 como imagem base.
* Usando a rede locadora-network que foi criado para fazer a comunicação entre os meus serviços.
* liberei a porta 6603 do host fazendo a comunicação para a porta 3306 do serviço.
* Passei as variáveis mais importantes para quando o meu serviço subir, passar esses parametros para o mysql.

* Criei um novo serviço chamado locadora-rest-api utilizando a imagem da aplicação como base.
* Disse que ele depende do serviço db para funcionar, ou seja, ele jamais funcionará sem o mysql junto.
* Passei a network que ele irá trabalhar.
* Portas que estará rodando no meu host e no serviço
* Variáveis de ambiente para ele se comunicar com o banco de dados, qual database ele irá funcionar, nome do usuario e a porta onde encontra-se o serviço. E claro, o perfil no qual a minha aplicação irá funcionar.
* E por último, passamos um commando para ele quando for subir. É o mesmo que: 
````
java -jar locadora.jar -Dspring.profiles.active=dev
````  

## Aplicação no AR? Então bora testar.

Após subir a apliação do modo que desejas, faça um pequeno teste com o comando: 
````$xslt
curl http://localhost:8091/
````
Resultado esperado: 
````$xslt
{"timestamp":"2019-04-29T21:02:01.320+0000","status":404,"error":"Not Found","message":"No message available","path":"/"}
````

Desconsidere o timestamp é claro. 

Agora que você possui a sua aplicação rodando, dê uma olhada na documentação REST API, acessando o link: 

[Swagger](http://localhost:8091/swagger-ui.html) Toda a documentação de como usar a API para possíveis integrações está aqui, usado o framework Swagger para criar..

Então é isso, até mais =D