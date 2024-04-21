FROM clojure:tools-deps-1.11.1.1237-jammy

ARG datomic_version=1.0.7075

ENV DATOMIC_VERSION $datomic_version
ENV DATOMIC_HOME /opt/datomic-pro-$datomic_version
ENV DATOMIC_DATA $DATOMIC_HOME/data

RUN apt-get update && apt-get install -y unzip

ADD datomic-pro-${DATOMIC_VERSION}.zip /datomic-pro.zip
RUN unzip /datomic-pro.zip -d /opt && rm /datomic-pro.zip

WORKDIR $DATOMIC_HOME

RUN echo DATOMIC HOME: $DATOMIC_HOME

COPY dev-transactor.properties config/transactor.properties
COPY start.sh start.sh
RUN chmod 777 start.sh
ENTRYPOINT ["./start.sh"]

VOLUME $DATOMIC_DATA

EXPOSE 4334 4335 4336 8080
