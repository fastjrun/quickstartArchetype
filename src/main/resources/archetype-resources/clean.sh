#!/bin/bash

echo "clean ..."
# rm ${rootArtifactId}-api
rm -rf ${rootArtifactId}-api/src/

# rm ${rootArtifactId}-api-test
rm -rf ${rootArtifactId}-api-test/src/main/
rm -rf ${rootArtifactId}-api-test/src/test/java/
rm -rf ${rootArtifactId}-api-test/src/test/resources/testng.xml
rm -rf ${rootArtifactId}-api-test/src/test/data/

# rm ${rootArtifactId}-base
rm -rf ${rootArtifactId}-base/src/

# rm ${rootArtifactId}-api
rm -rf ${rootArtifactId}-api/src/

# rm ${rootArtifactId}-bundle
rm -rf ${rootArtifactId}-bundle/src/

# rm ${rootArtifactId}-bundle-mock
rm -rf ${rootArtifactId}-bundle-mock/src/

mvn clean
echo "clean done."