mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Pcoverage-per-test
mvn sonar:sonar
