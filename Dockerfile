FROM sbtscala/scala-sbt:eclipse-temurin-17.0.4_1.8.0_3.2.1 as builder

WORKDIR /work/build

COPY minesweeper minesweeper
COPY immutable-scalajs immutable-scalajs
COPY project project
COPY resources resources
COPY index.html .
COPY build.sbt .
COPY build.sh .

RUN sed 's/\r$//' build.sh > build_fixed.sh
RUN chmod +x build_fixed.sh

RUN bash build_fixed.sh

FROM nginx:1.23-alpine

WORKDIR /work/build/out

COPY --from=builder /work/build/out /usr/share/nginx/html
