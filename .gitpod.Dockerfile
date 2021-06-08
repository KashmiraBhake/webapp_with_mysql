FROM gitpod/workspace-mysql
 
ARG SSL_KEYSTORE_PASSWORD
USER root
RUN apt-get update \
 && apt-get install -y mysql-server \
 && apt-get clean && rm -rf /var/cache/apt/* /var/lib/apt/lists/* /tmp/* \
 && mkdir /var/run/mysql \
 && chown -R gitpod:gitpod /etc/mysql /var/run/mysqld /var/log/mysql /var/lib/mysql /var/lib/mysql-files /var/lib/mysql-keyring /var/lib/mysql-upgrade
USER kb


ARG SSL_KEYSTORE_PASSWORD
USER root
RUN mkdir -p /tmp/mysql && \
    cd /tmp/mysql && \
    wget https://osdn.net/projects/sfnet_mysqljson/downloads/mysqljsonimport/mysqljsonimport_1.6/mysqljsonimport-1.6.tar.gz && \
    tar xf mysql.tgz && \
    cd mysql-* && \
    sudo cp bin/* /usr/local/bin/ && \
    rm -rf /tmp/mysql && \
    sudo mkdir -p /data/db && \
    sudo chown gitpod:gitpod 
USER kb
