#!/bin/bash

mkdir /Users/travis/PunishmentManager-build_$1
mv "/Users/travis/.m2/repository/me/superbiebel/PunishmentManagerCore/NOT-REAL-VERSION/PunishmentManagerCore-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/PunishmentManagerCore-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultehcache-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultehcache-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultdatahandler-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultdatahandler-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultmysqldatabase-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultmysqldatabase-build_$1.jar"
cd /Users/travis/
tar -cvzf PunishmentManager-build_$1.tar PunishmentManager-build_$1


curl -T /Users/travis/PunishmentManager-build_$1.tar -usuperbiebel:$2 -X PUT https://api.bintray.com/content/moderationmanager/PunishmentManager/RELEASE/build_$1/PunishmentManager-build_$1.tar\?publish=1
curl -T /Users/travis/PunishmentManager-build_$1/PunishmentManagerCore-build_$1.jar -usuperbiebel:$2 -X PUT https://api.bintray.com/content/moderationmanager/PunishmentManager/RELEASE/build_$1/PunishmentManager-build_$1.jar\?publish=1