FROM hseeberger/scala-sbt
WORKDIR /dominion
ADD . /dominion
CMD sbt run