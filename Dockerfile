FROM amazoncorretto:17.0.6-alpine as builder

WORKDIR /WhatsATTManagerBatch

COPY ./mvnw .
COPY ./pom.xml .
COPY ./.mvn ./.mvn

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:17.0.6-alpine

WORKDIR /WhatsATTManagerBatch

COPY --from=builder /WhatsATTManagerBatch/target/WhatsATTManagerBatch.jar .

EXPOSE 7050

ENTRYPOINT [ "java","-jar","WhatsATTManagerBatch.jar" ]
