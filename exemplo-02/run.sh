cd app && mvn clean package && cd ..
docker image build -t ricardojob/app2 ./app
docker container run -p 8081:8080 --name app ricardojob/app2
# echo 'fim'
