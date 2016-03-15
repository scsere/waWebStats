# waWebStats

API written in java to interact with the Whatsapp web client using Selenium WebDriver.

## Setup

A few simple steps to setup an environment to build the project.

### Browser

Create a new profile for your browser e.g. Firefox:
```sh
firefox -p
```

go to http://web.whatsapp.com, make sure the 'remember me' checkbox is checked and scan the QR code with your phone, close the browser, done.
In case of problems make sure the browser keeps the session cookie required to authenticate.

### Java

Download the latest stable version of Selenium Web Driver from http://www.seleniumhq.org/, extract it and add it to the applications buildpath.

## Use

```java
//To Initialize the api:
WaWebStats waWeb = new WaWebStats("profileName"); //No param for default "SELENIUM"

//Load the driver e.g. Firefox
waWeb.initFirefox();
```

## Examples
For example programs see the com.scsere.examples package:

+ EchoServer.java
+ Logger.java
+ ConversationPrinter.java

#### IntelliJ-Idea
To run the example programs first build the artifacts then navigate to out/artifacts/\<artifact name\> (be sure to configure a profile in your browser first) and run:
```bash
java -jar <artifact name>.jar
```