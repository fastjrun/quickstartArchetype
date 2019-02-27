#!/bin/bash

echo "build ..."
if [ "local_bundle_mock" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-bundle-mock -am -Dbdmgc.skip=false -Dcodeg.author=${author}
elif [ "provider_mock" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-provider-mock -am -Dbdmgc.skip=false -Dcodeg.author=${author}
    cp ${rootArtifactId}-provider-mock/target/${rootArtifactId}-provider-mock.war ~/server/tomcat8/webapps/${rootArtifactId}-provider-mock.war
elif [ "package_api_test" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-api-test -am -Dclientgc.skip=false -Dcodeg.author=${author}
elif [ "unitTest" = $1 ] ; then
    mvn clean verify -pl ${rootArtifactId}-test -PunitTest -am
elif [ "mock_test" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-api-test -Dclientgc.skip=false -Dcodeg.author=${author} -Pmock
elif [ "package_provider" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-provider -am -Dbasegc.skip=false -Dbdgc.skip=false -Dcodeg.author=${author} -P$2
    cp ${rootArtifactId}-provider/target/${rootArtifactId}-provider.war ~/server/tomcat8/webapps/${rootArtifactId}-provider.war
elif [ "api_test" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-api-test -Dclientgc.skip=false -Dcodeg.author=${author} -P$2
elif [ "package_task" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-task -am -Dbasegc.skip=false -Dbdgc.skip=false -Dcodeg.author=${author} -P$2
    rm -rf ~/app/${rootArtifactId}/${rootArtifactId}-task
    cp -r ${rootArtifactId}-task/target/${rootArtifactId}-task/${rootArtifactId}-task ~/app/${rootArtifactId}
elif [ "package_ci" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-base -am -Dbasegc.skip=false -Dcodeg.author=${author}
    mvn clean package -pl ${rootArtifactId}-bundle -am -Dbdgc.skip=false -Dcodeg.author=${author}
    mvn clean package -pl ${rootArtifactId}-api-test -am -Dclientgc.skip=false -Dcodeg.author=${author} -Dmaven.test.skip=true
    mvn clean package -pl ${rootArtifactId}-bundle-mock -am -Dbdmgc.skip=false -Dcodeg.author=${author}
    mvn clean package -pl ${rootArtifactId}-api -am -Dapigc.skip=false -Dcodeg.author=${author}
elif [ "service_ut" = $1 ] ; then
    mvn clean package -pl ${rootArtifactId}-test -am -Dbasegc.skip=false -Dbdgc.skip=false -Dcodeg.author=${author} -P$2
fi
echo "build done."