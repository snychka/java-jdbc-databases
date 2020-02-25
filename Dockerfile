FROM maven:3.6.3-jdk-11-slim

WORKDIR /usr/app

# mvn needs a user.home in which to run as non-root
# https://hub.docker.com/_/maven , "Running as non-root"
# hence the mkdir and USER command later on
RUN ["mkdir", "/home/projects"]

RUN groupadd projects && useradd -g projects projects && \
  chown -R projects:projects /usr/app && \
  chown -R projects:projects /home/projects

# needed for mvn, see above
USER projects

COPY --chown=projects:projects . .

RUN ["mvn", "clean"]

RUN ["mvn", "de.qaware.maven:go-offline-maven-plugin:resolve-dependencies", "-P", "integration"]

RUN ["mvn", "package"]

ENTRYPOINT ["java", "-jar", "./target/orders.jar"]
