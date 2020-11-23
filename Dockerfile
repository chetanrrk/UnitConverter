FROM anapsix/alpine-java:8_jdk
LABEL maintainer="chetanrrk@gmail.com"

ARG APPLICATION_SECRET="converter"
ENV APPLICATION_SECRET=${APPLICATION_SECRET}

# sbt
ENV SBT_URL=https://dl.bintray.com/sbt/native-packages/sbt
ENV SBT_VERSION 0.13.15
ENV INSTALL_DIR /usr/local
ENV SBT_HOME /usr/local/sbt
ENV PATH ${PATH}:${SBT_HOME}/bin

# Install sbt
RUN apk add --no-cache --update bash wget && mkdir -p "$SBT_HOME" && \
    wget -qO - --no-check-certificate "https://dl.bintray.com/sbt/native-packages/sbt/$SBT_VERSION/sbt-$SBT_VERSION.tgz" |  tar xz -C $INSTALL_DIR && \
    echo -ne "- with sbt $SBT_VERSION\n" >> /root/.built && \
    apk add --no-cache unzip

WORKDIR /unit-converter
COPY . /unit-converter

RUN sbt dist && \
    unzip /unit-converter/target/universal/*.zip -d /unit-converter/dist/ && \
    mv /unit-converter/dist/unit-converter* /unit-converter/dist/unit-converter

EXPOSE 9000
ENTRYPOINT /bin/bash /unit-converter/dist/unit-converter/bin/unit-converter -Dplay.http.secret.key=$APPLICATION_SECRET
