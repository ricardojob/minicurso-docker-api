# manualmente
docker build -t ricardojob/bd ./postgres
docker run -p 5433:5432 --name bd -d ricardojob/bd 
cd app && mvn clean package && cd ..
docker build -t ricardojob/app ./app
docker run -p 8082:8080 --name app --link bd:host-banco ricardojob/app
#docker-compose
#docker-compose up --build