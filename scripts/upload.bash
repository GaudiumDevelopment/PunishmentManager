#!/bin/bash
mv /Users/travis/.m2/repository/me/superbiebel/PunishmentManager/NOT-REAL-VERSION/PunishmentManager-NOT-REAL-VERSION.jar /Users/travis/.m2/repository/me/superbiebel/PunishmentManager/NOT-REAL-VERSION/PunishmentManager-DEV_build_$1.jar
curl -T /Users/travis/.m2/repository/me/superbiebel/PunishmentManager/NOT-REAL-VERSION/PunishmentManager-DEV_build_$1.jar -usuperbiebel:$2 https://api.bintray.com/content/moderationmanager/PunishmentManager/INDEV/DEV_build_$1/
sleep 10
curl -usuperbiebel:$2 -X POST https://api.bintray.com/content/moderationmanager/PunishmentManager/INDEV/DEV_build_$1/publish
