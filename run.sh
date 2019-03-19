#!/bin/bash
cd `dirname $0`

LOGFILE=/var/log/signal/server-access.log
CONFIG=/opt/signal-config/server/config.yml

if [ "--background" = "$1" ] || [ "-b" = "$1" ]; then
	nohup java -jar target/TextSecureServer-2.26.jar server $CONFIG > $LOGFILE 2>&1 &
else
	java -jar target/TextSecureServer-2.26.jar server $CONFIG
fi;
