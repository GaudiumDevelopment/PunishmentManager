#!/bin/bash
mv /Users/travis/.m2/repository/me/superbiebel/PunishmentManager/NOT-REAL-VERSION/PunishmentManager-NOT-REAL-VERSION.jar /Users/travis/.m2/repository/me/superbiebel/PunishmentManager/NOT-REAL-VERSION/PunishmentManager-INDEV_build_$1.jar
curl -T /Users/travis/.m2/repository/me/superbiebel/PunishmentManager/NOT-REAL-VERSION/PunishmentManager-INDEV_build_$1.jar -usuperbiebel:$2 -X PUT https://api.bintray.com/content/moderationmanager/PunishmentManager/INDEV/INDEV_build_$1/PunishmentManager-INDEV_build_$1.jar\?publish=1
