# parando o container com o nome 'app'
docker stop app
# revemor o container com o nome 'app'
docker rm app
#removendo as images
docker rmi -f ricardojob/app

docker stop bd
docker rm bd
docker rmi -f ricardojob/bd

#docker-compose
#docker-compose down