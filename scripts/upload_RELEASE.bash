#!/bin/bash
mv /Users/travis/.m2/repository/me/superbiebel/PunishmentManagerCore/NOT-REAL-VERSION/PunishmentManagerCore-NOT-REAL-VERSION.jar /Users/travis/.m2/repository/me/superbiebel/PunishmentManagerCore/NOT-REAL-VERSION/PunishmentManagerCore-build_$1.jar
curl -T /Users/travis/.m2/repository/me/superbiebel/PunishmentManagerCore/NOT-REAL-VERSION/PunishmentManagerCore-build_$1.jar -usuperbiebel:$2 -X PUT https://api.bintray.com/content/moderationmanager/PunishmentManager/STABLE/build_$1/PunishmentManagerCore-build_$1.jar\?publish=1
