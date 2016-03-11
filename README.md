# waWebStats

API to interact with Whatsapp web client using the Selenium lib.

## Setup

Create a new profile for your browser e.g. Firefox:
```sh
firefox -p
```

go to http://web.whatsapp.com, make sure the 'remember me' checkbox is checked and scan the QR code with your phone, close the browser, done.
In case of problems make sure the browser keeps the session cookie required to authenticate.

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