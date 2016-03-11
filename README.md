# waWebStats

API to interact with Whatsapp web client using the Selenium lib.

## Setup

Create a new profile for your browser e.g. Firefox:
```sh
firefox -p
```

go to http://web.whatsapp.com and scan the QR code with your phone, close the browser, done.

## Use

```java
//To Initialize the api:
WaWebStats waWeb = new WaWebStats("profileName"); //No param for default "SELENIUM"

//Load the driver e.g. Firefox
waWeb.initFirefox();
```

## Examples
For an example program see Main.java