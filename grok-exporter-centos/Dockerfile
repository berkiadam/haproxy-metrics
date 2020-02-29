FROM centos:7
LABEL Maintainer="Adam Berki <https://github.com/berkiadam/>"
LABEL Name="grok_exporter"
LABEL Version="0.2.8"

ENV PID_DIR /tmp/pidDir
ENV GROK_ARCH="grok_exporter-0.2.8.linux-amd64"
ENV GROK_VERSION="v0.2.8"

USER root

RUN  yum -y install rsyslog wget unzip && \
	 yum clean all && \
	 echo "" > /etc/rsyslog.d/listen.conf && \
	 mkdir -p ${PID_DIR} && \
	 chmod 777 ${PID_DIR} \
	 && wget https://github.com/fstab/grok_exporter/releases/download/$GROK_VERSION/$GROK_ARCH.zip \
	 && unzip $GROK_ARCH.zip \
	 && mv $GROK_ARCH /grok \
	 && rm $GROK_ARCH.zip \
	 && yum -y remove wget unzip \
	 && rm -fr /var/lib/apt/lists/*

RUN mkdir -p /etc/grok_exporter && ln -sf /etc/grok_exporter/config.yml /grok/

COPY rsyslog.conf /etc/rsyslog.conf
COPY log_rotation_script /grok/log_rotation_script

RUN chmod 777 /grok/log_rotation_script

EXPOSE 514/tcp 514/udp 9144/tcp
WORKDIR /grok

CMD  sh -c "nohup /usr/sbin/rsyslogd -i ${PID_DIR}/pid -n &" && ./grok_exporter -config /grok/config.yml
