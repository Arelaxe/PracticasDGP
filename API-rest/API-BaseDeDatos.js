require('dotenv').config();

const Express = require("express");
const BodyParser = require("body-parser");
const MongoClient = require("mongodb").MongoClient;
const ObjectId = require("mongodb").ObjectID;
const CONNECTION_URL = process.env.MONGO_CONNECTION_URL;
const DATABASE_NAME = process.env.DATABASE_NAME;

var app = Express();
app.use(BodyParser.json());
app.use(BodyParser.urlencoded({ extended: true }));

var database, collectionAdmin, collectionFacilitadores;

app.listen(5000, () => {
    MongoClient.connect(CONNECTION_URL, { useNewUrlParser: true, useUnifiedTopology: true }, (error, client) => {
        if(error) {
            throw error;
        }
        database = client.db(DATABASE_NAME);
        collectionAdmin = database.collection("Administradores");
        collectionFacilitadores = database.collection("Facilitadores");
        console.log("Connected to `" + DATABASE_NAME + "`!");
    });
});

app.post("/facilitadores", (request, response) => {
    collectionFacilitadores.insertOne(request.body, (error, result) => {
        if(error) {
            return response.status(500).send(error);
        }
        console.log(request.body);
        response.send(result.result);
    });
});

/******************************************************/
// Login Facilitadores
/******************************************************/
app.get("/login-facilitador", (request, response) => {
    collectionFacilitadores.findOne({ "username":request.query.username.toLowerCase() }, (error, result) => { //quizás es con request.params
        if(error) {
            
            return response.status(500).send(error);
        }
        if (result == null){
            response.send("Nombre de usuario incorrecto");
        }
        else{
            collectionFacilitadores.findOne({ "username":request.query.username.toLowerCase(),"password":request.query.password }, (errorPass, resultPass) => {
                if (resultPass == null){
                    response.send("Contraseña incorrecta");
                }
                else{
                    response.send("Has iniciado sesión");
                }
            }
        );
        }
    });
});

/******************************************************/
// Login Administradores
/******************************************************/
app.get("/login-administrador", (request, response) => {
    collectionAdmin.findOne({ "username":request.query.username.toLowerCase() }, (error, result) => { //quizás es con request.params
        if(error) {
            
            return response.status(500).send(error);
        }
        if (result == null){
            response.send("Nombre de usuario incorrecto");
        }
        else{
            collectionAdmin.findOne({ "username":request.query.username.toLowerCase(),"password":request.query.password }, (errorPass, resultPass) => {
                if (resultPass == null){
                    response.send("Contraseña incorrecta");
                }
                else{
                    response.send("Has iniciado sesión");
                }
            }
        );
        }
    });
});