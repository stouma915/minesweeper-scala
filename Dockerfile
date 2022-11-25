FROM sbtscala/scala-sbt:eclipse-temurin-17.0.4_1.8.0_3.2.1 as builder

WORKDIR /work/build

COPY project project
COPY src src
COPY resources resources
COPY index.html .
COPY build.sbt .
COPY build.sh .

RUN bash build.sh

FROM nginx:1.23-alpine

WORKDIR /work/build/out

COPY --from=builder /work/build/out /usr/share/nginx/html
