# parando o container com o nome 'app'
docker container stop app
# revemor o container com o nome 'app'
docker container rm app
#removendo as images
docker image rm -f ricardojob/app

docker container stop  bd
docker container rm bd
docker image rm -f ricardojob/bd