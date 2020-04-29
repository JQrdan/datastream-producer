# datastream-producer

High volume, multithreaded producer.

## Required data

This project requires you to have access to this dataset: https://webscope.sandbox.yahoo.com/catalog.php?datatype=r&did=2

## How to build

`mvn clean package`

## How to run

  1. Edit the `Makefile` to include the correct path for the files you want to read into kafka. Default location is `../ydata/`
  2. run `make genres` to produce genre data
  3. run `make song-attributes` to produce song attributes data
  4. run `make songs` to produce song data