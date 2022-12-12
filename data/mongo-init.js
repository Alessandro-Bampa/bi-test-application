//initialize db on docker startup
db = db.getSiblingDB("bi_test_db")
db.createCollection('items')

db.items.insertMany([
    {
      "created": new Date(),
      "tags": [
        "tag2"
      ],
      "updated": new Date(),
      "value": 5
    },{
      "created": new Date(),
      "tags": [
        "tag1",
        "tag2"
      ],
      "updated": new Date(),
      "value": 10
    },{
      "created": new Date(),
      "tags": [
        "tag3",
        "tag4",
        "tag5"
      ],
      "updated": new Date(),
      "value": 15
    },{
      "created": new Date(),
      "tags": [
        "tag1"
      ],
      "updated": new Date(),
      "value": 20
    },{
      "created": new Date(),
      "tags": [
        "tag1",
        "tag3"
      ],
      "updated": new Date(),
      "value": 25
    },{
      "created": new Date(),
      "tags": [
        "tag1",
        "tag2"
      ],
      "updated": new Date(),
      "value": 30
    },{
      "created": new Date(),
      "tags": [
        "tag5",
        "tag2"
      ],
      "updated": new Date(),
      "value": 35
    },{
      "created": new Date(),
      "tags": [
        "tag1",
        "tag2",
        "tag3",
        "tag4",
        "tag5"
      ],
      "updated": new Date(),
      "value": 40
    },{
      "created": new Date(),
      "tags": [
        "tag1",
        "tag4",
        "tag6"
      ],
      "updated": new Date(),
      "value": 100
    },{
      "created": new Date(),
      "tags": [
        "tag4"
      ],
      "updated": new Date(),
      "value": 210
    },{
      "created": new Date(),
      "tags": [
        "tag4",
        "tag2"
      ],
      "updated": new Date(),
      "value": 90
    },{
      "created": new Date(),
      "tags": [
        "tag3",
        "tag2",
        "tag5"
      ],
      "updated": new Date(),
      "value": 56
    }
])

//create a TTL index
db.items.createIndex( { "updated": -1 }, { expireAfterSeconds: 600 } )