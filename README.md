# The Stolen Text
A project created in SOFTENG 206: Software Engineering Design 1.
![image](https://github.com/user-attachments/assets/125a191c-245b-4fe6-8c4c-a0507270879d)
![image](https://github.com/user-attachments/assets/78b77183-4df1-4315-bc4e-72784e45c9d8)

## Developers:
- Lewis Azzopardi
- Connie Ding
- Charan Gazula

## To setup the API to access Chat Completions and TTS

- add in the root of the project (i.e., the same level where `pom.xml` is located) a file named `apiproxy.config`
- put inside the credentials that you received from no-reply@digitaledu.ac.nz (put the quotes "")

  ```
  email: "UPI@aucklanduni.ac.nz"
  apiKey: "YOUR_KEY"
  ```
  These are your credentials to invoke the APIs. 

## To run the game

`./mvnw clean javafx:run`

## To debug the game

`./mvnw clean javafx:run@debug` then in VS Code "Run & Debug", then run "Debug JavaFX"

## To run codestyle

`./mvnw clean compile exec:java@style`
