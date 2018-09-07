	https://stackoverflow.com/questions/44278066/how-to-use-criteria-queries-in-spring-boot-data-jpa-application
    https://vladmihalcea.com/14-high-performance-java-persistence-tips/
    https://www.atlassian.com/git/tutorials/syncing/git-fetch

musicbrainz slave server with search and replication
==================

[![Build Status](https://travis-ci.org/metabrainz/musicbrainz-docker.svg?branch=master)](https://travis-ci.org/metabrainz/musicbrainz-docker)

### Installation

###### Install and Start
* Make sure you have installed docker and docker-compose then:
* `git clone https://github.com/metabrainz/musicbrainz-docker.git`
* `cd musicbrainz-docker`
* `sudo docker-compose up -d`
* or to expose the db and redis ports: `sudo docker-compose -f docker-compose.yml -f docker-compose.public.yml up -d`
* Set the token you got from musicbrainz (instructions for generating a token are [here](http://blog.musicbrainz.org/2015/05/19/schema-change-release-2015-05-18-including-upgrade-instructions/)).
* `sudo docker exec musicbrainzdocker_musicbrainz_1 /set-token.sh <replication token>`

### Create database
Create the database, download the latest dumps and populate the database

* `sudo docker-compose run --rm musicbrainz /createdb.sh -fetch`

Create the database, and populate the database with existing dumps

* `sudo docker-compose run --rm musicbrainz /createdb.sh`

### Replication
Replication is run as a cronjob, you can update the [crons.conf](musicbrainz-dockerfile/scripts/crons.conf) file to change when replication will be run.

To view the replication log file you can run this command
* `sudo docker-compose exec musicbrainz /usr/bin/tail -f slave.log`

### If you need to recreate the database
you will need to enter the postgres password that you set in [postgres.env](postgres-dockerfile/postgres.env).
* `sudo docker-compose run --rm musicbrainz /recreatedb.sh`
or to recreate and fetch new data dumps
* `sudo docker-compose run --rm musicbrainz /recreatedb.sh -fetch`

###### The usual process to update the schema is:

* Ensure you’ve replicated up to the most recent replication packet available with the old schema. (if you’re not sure, run `sudo docker exec musicbrainzdocker_musicbrainz_1 /replication.sh`).
* Switch to the new code with:
* Run bash in the container: `sudo docker exec -ti musicbrainzdocker_musicbrainz_1 bash`.
* Checkout the new branch: `git fetch origin && git checkout NEW_SCHEMA_BRANCH`.
* Run the upgrade script: `./upgrade.sh`.
* Exit bash `exit`.
* Set DB_SCHEMA_SEQUENCE to the NEW_SCHEMA_NUM in the [DBDefs.pm file](musicbrainz-dockerfile/DBDefs.pm#L95)
* `sudo docker-compose stop musicbrainz` then `sudo docker-compose build musicbrainz` then `sudo docker-compose up -d --no-deps musicbrainz`
