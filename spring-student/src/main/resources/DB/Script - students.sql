con = new Mongo()
db = conn.getDB("Students")
db.createCollection("students")

db.students.insert([
{
"_id" : ObjectId("5d7ffc1500eb361c50a93f07"),
"name" : "Flor",
"gender" : "Femenino",
"birthdate" : ISODate("2017-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "88885588",
"createdAt" : ISODate("2019-09-16T21:18:13.418Z")
},
{
"_id" : ObjectId("5d7ffc2600eb361c50a93f08"),
"name" : "Maria",
"gender" : "Femenino",
"birthdate" : ISODate("2010-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "98989898",
"createdAt" : ISODate("2019-09-16T21:18:30.255Z")
},
{
"_id" : ObjectId("5d7ffc3100eb361c50a93f09"),
"name" : "Lucia",
"gender" : "Femenino",
"birthdate" : ISODate("2009-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20191010",
"createdAt" : ISODate("2019-09-16T21:18:41.873Z")
},
{
"_id" : ObjectId("5d7ffc3e00eb361c50a93f0a"),
"name" : "Mariana",
"gender" : "Femenino",
"birthdate" : ISODate("2008-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20081010",
"createdAt" : ISODate("2019-09-16T21:18:54.403Z")
},
{
"_id" : ObjectId("5d7ffc4600eb361c50a93f0b"),
"name" : "Romina",
"gender" : "Femenino",
"birthdate" : ISODate("2007-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20071010",
"createdAt" : ISODate("2019-09-16T21:19:02.719Z")
},
{
"_id" : ObjectId("5d7ffc5000eb361c50a93f0c"),
"name" : "Estefany",
"gender" : "Femenino",
"birthdate" : ISODate("2006-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20061010",
"createdAt" : ISODate("2019-09-16T21:19:12.674Z")
},
{
"_id" : ObjectId("5d7ffc5a00eb361c50a93f0d"),
"name" : "Teresa",
"gender" : "Femenino",
"birthdate" : ISODate("2005-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20051010",
"createdAt" : ISODate("2019-09-16T21:19:22.956Z")
},
{
"_id" : ObjectId("5d7ffc6500eb361c50a93f0e"),
"name" : "Fiorella",
"gender" : "Femenino",
"birthdate" : ISODate("2004-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20041010",
"createdAt" : ISODate("2019-09-16T21:19:33.652Z")
},
{
"_id" : ObjectId("5d7ffc6e00eb361c50a93f0f"),
"name" : "Nathaly",
"gender" : "Femenino",
"birthdate" : ISODate("2003-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20031010",
"createdAt" : ISODate("2019-09-16T21:19:42.792Z")
},
{
"_id" : ObjectId("5d7ffc7a00eb361c50a93f10"),
"name" : "Valentina",
"gender" : "Femenino",
"birthdate" : ISODate("2002-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20021010",
"createdAt" : ISODate("2019-09-16T21:19:54.331Z")
},
{
"_id" : ObjectId("5d7ffc8700eb361c50a93f11"),
"name" : "Martha",
"gender" : "Femenino",
"birthdate" : ISODate("2001-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20011010",
"createdAt" : ISODate("2019-09-16T21:20:07.610Z")
},
{
"_id" : ObjectId("5d7ffc9b00eb361c50a93f12"),
"name" : "Luiggi",
"gender" : "Masculino",
"birthdate" : ISODate("2017-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20171010",
"createdAt" : ISODate("2019-09-16T21:20:27.782Z")
},
{
"_id" : ObjectId("5d7ffca500eb361c50a93f13"),
"name" : "Brayan",
"gender" : "Masculino",
"birthdate" : ISODate("2018-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20181010",
"createdAt" : ISODate("2019-09-16T21:20:37.951Z")
},
{
"_id" : "8",
"name" : "Jeffer",
"gender" : "Masculino",
"birthdate" : ISODate("2019-09-17T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "74306051",
"createdAt" : ISODate("2019-09-17T14:40:49.872Z")
},
{
"_id" : "9",
"name" : "Randyx",
"gender" : "Masculino",
"birthdate" : ISODate("2019-09-17T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "19771977",
"createdAt" : ISODate("2019-09-17T15:09:43.449Z")
},
{
"_id" : ObjectId("5d80f9e900eb36410cf065c4"),
"name" : "Robert",
"gender" : "Masculino",
"birthdate" : ISODate("2019-09-17T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "20090806",
"createdAt" : ISODate("2019-09-17T15:21:13.431Z")
},
{
"_id" : ObjectId("5d8138d200eb363838b4d738"),
"name" : "Martino",
"gender" : "Masculino",
"birthdate" : ISODate("2019-09-17T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "00000001",
"createdAt" : ISODate("2019-09-17T19:49:38.804Z")
},
{
"_id" : ObjectId("5d8153cb00eb36154036018c"),
"name" : "Forlan",
"gender" : "Masculino",
"birthdate" : ISODate("1997-10-10T00:00:00Z"),
"typeID" : "DNI",
"numberID" : "19971010",
"createdAt" : ISODate("2019-09-17T21:44:43.730Z")
}])