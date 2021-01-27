#!/bin/bash

bash scripts/package.bash $1

echo "Uploading Files!"
curl -T /Users/travis/PunishmentManager+default_modules-build_$1.tar -usuperbiebel:$2 -X PUT https://api.bintray.com/content/moderationmanager/PunishmentManager/RELEASE_CANDIDATE/build_$1/PunishmentManager+default_modules-build_$1.tar\?publish=1
curl -T /Users/travis/PunishmentManager-build_$1/PunishmentManagerCore-build_$1.jar -usuperbiebel:$2 -X PUT https://api.bintray.com/content/moderationmanager/PunishmentManager/RELEASE_CANDIDATE/build_$1/PunishmentManager-build_$1.jar\?publish=1
echo "Files uploading done!!"