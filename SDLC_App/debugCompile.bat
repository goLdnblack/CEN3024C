@ECHO OFF
javac --module-path javafx-sdk-19/lib --add-modules=javafx.graphics,javafx.controls,javafx.fxml,javafx.web *.java
pause