# OddSports

There is a live instance running in Heroku. In a terminal try:
```
curl 'https://oddsports-0c28269c3f7b.herokuapp.com/api/matches
```

Swagger-UI available also [Heroku](https://oddsports-0c28269c3f7b.herokuapp.com/swagger-ui/index.html)



### How to run

* Rename `.env-test` to `.env` and add the respective vars inside
* Runs with docker-compose

### Useful tips
- References to DB and tables are made in the form of `table.column` i.e. `match.id`.