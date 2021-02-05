#!/bin/bash

echo "Uploading build $1..."
echo "Making dir"
mkdir /Users/travis/PunishmentManager-build_$1
echo "Dir successfully made!"

echo "Moving and renaming jars..."
mv "/Users/travis/PunishmentManager/PunishmentManagerCore/build/libs/PunishmentManagerCore-NOT-REAL-VERSION-all.jar" "/Users/travis/PunishmentManagerCore-build_$1.jar"
mv "/Users/travis/PunishmentManager/DefaultEhCache/build/libs/defaultehcache-NOT-REAL-VERSION-all.jar" "/Users/travis/PunishmentManager-build_$1/defaultehcache-build_$1.jar"
mv "/Users/travis/PunishmentManager/DefaultDataHandler/build/libs/defaultdatahandler-NOT-REAL-VERSION-all.jar" "/Users/travis/PunishmentManager-build_$1/defaultdatahandler-build_$1.jar"
mv "/Users/travis/PunishmentManager/DefaultMysqlDatabase/build/libs/defaultmysqldatabase-NOT-REAL-VERSION-all.jar" "/Users/travis/PunishmentManager-build_$1/defaultmysqldatabase-build_$1.jar"
mv "/Users/travis/PunishmentManager/DefaultOffenseProcessor/build/libs/defaultoffenseprocessor-NOT-REAL-VERSION-all.jar" "/Users/travis/PunishmentManager-build_$1/defaultoffenseprocessor-build_$1.jar"
mv "/Users/travis/PunishmentManager/OffenseProcessingDataAbstraction/build/libs/offenseprocessingdataabstraction-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/offenseprocessingdataabstraction-build_$1.jar"
mv "/Users/travis/PunishmentManager/DefaultRedisMessaging/build/libs/defaultredismessaging-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultredismessaging-build_$1.jar"

echo "File moving completed!"

echo "Archiving files..."
cd /Users/travis/ || exit
tar -cvzf PunishmentManager+default_modules-build_$1.tar PunishmentManager-build_$1
echo "Files archived!"