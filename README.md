# Unit Converter App

## About this app
This application uses the play framework to convert a user given units into SI units. The layout of this application (play framework applications in general) can be found here https://www.playframework.com/documentation/2.8.x/Anatomy.

The endpoint is defined under conf/routes. 

The backend codes are at app/controllers

The test codes are at tests/controllers


## Run the application on a command line
```
./sbt run
```

##Test the application

```
./sbt test
```

## Docker Build

```
docker build -t citrine/unit-converter:1.0.0 .
```

## Docker Run

```
docker run -p 9000:9000 citrine/unit-converter:1.0.0
```

## Local Run
```
http://localhost:9000/units/si?units=(degree/min)
```
