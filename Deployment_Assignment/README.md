## Getting Started

The command to run the applications are:
java --enable-preview --module-path lib/javafx-sdk-19/lib --add-modules javafx.controls,javafx.graphics -jar DeploymentHost.jar
java --enable-preview --module-path lib/javafx-sdk-19/lib --add-modules javafx.controls,javafx.graphics -jar DeploymentClient.jar

## Folder Structure

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies, missing javafx-sdk-19

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.
