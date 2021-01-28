#!/bin/bash

echo "Uploading build $1..."
echo "Making dir"
mkdir /Users/travis/PunishmentManager-build_$1
echo "Dir successfully made!"

echo "Moving and renaming jars..."
mv "/Users/travis/.m2/repository/me/superbiebel/PunishmentManagerCore/NOT-REAL-VERSION/PunishmentManagerCore-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManagerCore-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultehcache/NOT-REAL-VERSION/defaultehcache-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultehcache-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultdatahandler/NOT-REAL-VERSION/defaultdatahandler-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultdatahandler-build_$1.jar"
mv "//Users/travis/.m2/repository/me/superbiebel/defaultmysqldatabase/NOT-REAL-VERSION/defaultmysqldatabase-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultmysqldatabase-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultoffenseprocessor/NOT-REAL-VERSION/defaultoffenseprocessor-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultoffenseprocessor-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/offenseprocessingdataabstraction/NOT-REAL-VERSION/offenseprocessingdataabstraction-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/offenseprocessingdataabstraction-build_$1.jar"
mv "/Users/travis/.m2/repository/me/superbiebel/defaultredismessaging/NOT-REAL-VERSION/defaultredismessaging-NOT-REAL-VERSION.jar" "/Users/travis/PunishmentManager-build_$1/defaultredismessaging-build_$1.jar"

echo "File moving completed!"

echo "Archiving files..."
cd /Users/travis/ || exit
tar -cvzf PunishmentManager+default_modules-build_$1.tar PunishmentManager-build_$1
echo "Files archived!"