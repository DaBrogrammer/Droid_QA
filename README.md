# COEN6761 SUMMER PROJECT
Summer 2023 Testing and validation Project
### System Prerequisite

#### To Run Application
To get started with running the project

1. Check the build and dependency status
   ```shell
    mvn dependency:resolve
    mvn package
   ```
2. If the dependency issue not resolved, add it externally from [Maven Repository](https://mvnrepository.com/). This issue arises when java version of local and pom.xml is mismatch.
3. Download jar [javafx(controls,fxml)](https://gluonhq.com/products/javafx/).
4. Download jar [controlsfx](https://mvnrepository.com/artifact/org.controlsfx/controlsfx/11.1.0)
5. Download jar [validatorfx](https://mvnrepository.com/artifact/net.synedra/validatorfx/0.1.13)
6. Go to File -> Project Structure -> Modules -> dependencies and all the externally downloaded jar.
7. Follow Step 1 again. If dependency issue exists use step 2 to download and add externally.
8. Use the IDE to run.

#### To Test Application

1. Edit the JUnit run-configuration in IDE to point to all or any of the test cases.
2. Run the @Test individually by clicking run on the left side of each method.

#### To run Coverage using JaCoCo
1. In order to generate test cases and the jacoco report run the shell command given below in the project's directory where Pom.xml is present.
    ```shell
    mvn clean install
   ```  

#### UNIVERSITY
[CONCORDIA UNIVERSITY](https://www.concordia.ca/).
