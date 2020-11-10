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
// var jsonParser = BodyParser.json();

var database, collectionUsuarios, collectionGrupos;

app.listen(5000, () => {
    MongoClient.connect(CONNECTION_URL, { useNewUrlParser: true, useUnifiedTopology: true }, (error, client) => {
        if(error) {
            throw error;
        }
        database = client.db(DATABASE_NAME);
        collectionUsuarios = database.collection("Usuarios");
        collectionTareas = database.collection("Tareas");
        collectionGrupos = database.collection("Grupos");
        console.log("Connected to `" + DATABASE_NAME + "`!");
    });
});

/******************************************************/
// Registro Usuarios
/******************************************************/
app.post("/registro", (request, response) => {
    collectionUsuarios.insertOne(request.body, (error, result) => {
        if(error) {
            return response.status(500).send(error);
        }
        if (result == null){
            response.send("Registro incorrecto");
        }
        else{    
            response.send("Registro completado");
        }
    });
});

/******************************************************/
// Login Usuarios
/******************************************************/
app.post("/login", (request, response) => {

    collectionUsuarios.findOne({ "username":request.body.username }, (error, result) => {
        if(error) {
            return response.status(500).send(error);
        }
        if (result == null){
            response.send("Nombre de usuario incorrecto");
        }
        else{
            collectionUsuarios.findOne({ "username":request.body.username,"password":request.body.passwd }, (errorPass, resultPass) => {
                if(errorPass) {
                    return response.status(500).send(errorPass);
                }
                if (resultPass == null){
                    response.send("Contraseña incorrecta");
                }
                else{
                    response.send(result.rol); //Si ha iniciado sesión correctamente, se le responde con su rol
                }
            }
        );
        }
    });
});

/******************************************************/
// Login Socios
/******************************************************/
app.post("/login-socio", (request, response) => {

    collectionUsuarios.findOne({ "rol" : "socio", "username":request.body.username }, (error, result) => {
        if(error) {
            return response.status(500).send(error);
        }
        if (result == null) { //Comprobación adicional que nunca debe fallar (la comprobación del usuario se realiza en pasos anteriores)
            var jsonRespuestaIncorrecta = JSON.parse('{"exito":2}'); //Fallo del usuario
            response.send(jsonRespuestaIncorrecta);
        }
        else{
            collectionUsuarios.findOne({ "rol" : "socio", "username":request.body.username,"password":request.body.passwd }, (errorPass, resultPass) => {

                if(errorPass) {
                    return response.status(500).send(errorPass);
                }
                if (resultPass == null){
                    var jsonRespuestaIncorrecta = JSON.parse('{"exito":0}'); //Fallo de la contraseña
                    response.send(jsonRespuestaIncorrecta);
                }
                else{
                    var jsonRespuestaCorrecta = JSON.parse('{"exito":1}');
                    response.send(jsonRespuestaCorrecta); //Exito en login
                }
            }
        );
        }
    });
});

/******************************************************/
// Listado administradores
/******************************************************/
app.get("/listado-administradores", (request, response) => {
    collectionUsuarios.find({"rol":{"$in": ["admin","ambos"]}}).toArray(function (error, result){
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No hay administradores");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Listado facilitadores
/******************************************************/
app.get("/listado-facilitadores", (request, response) => {
    collectionUsuarios.find({ "rol": { "$in": ["facilitador", "ambos"] } }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No hay facilitadores");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Listado socios
/******************************************************/
app.get("/listado-socios", (request, response) => {
    collectionUsuarios.find({ "rol": "socio" }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No hay socios");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Crear tarea
/******************************************************/
app.post("/crear-tarea", (request, response) => {
    collectionTareas.insertOne(request.body, (error, result) => {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("Fallo en la creación");
        }
        else {
            response.send("Creación completada");
        }
    });
});

/******************************************************/
// Eliminar tarea
/******************************************************/
app.post("/eliminar-tarea", (request, response) => {
    collectionTareas.deleteOne({ "creador": request.body.username, "nombre":request.body.nombreTarea }, (error, result) => {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            var jsonRespuestaIncorrecta = JSON.parse('{"exito":0}');
            response.send(jsonRespuestaIncorrecta);
        }
        else {
            var jsonRespuestaCorrecta = JSON.parse('{"exito":1}');
            response.send(jsonRespuestaCorrecta);
        }
    });
});

/******************************************************/
// Listado tareas (La funcionalidad ahora mismo es un "mis tareas" de facilitador)
/******************************************************/
app.post("/listado-tareas", (request, response) => {
    collectionTareas.find({ "creador": request.body.username }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("¡Aún no has creado tareas!");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Mis socios
/******************************************************/
app.post("/mis-socios", (request, response) => {
    collectionUsuarios.find({ "username": request.body.username }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("¡Aún no tienes socios a cargo!");
        }
        else {
            response.send(result);
        }
    });
});
/******************************************************/
// Perfil
/******************************************************/
app.post("/perfil", (request, response) => {
    collectionUsuarios.find({ "username":request.body.username }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("Perfil no encontrado");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Existe usuario
/******************************************************/
app.get("/existe-usuario", (request, response) => {
    collectionUsuarios.find({ "rol" : "socio", "username":request.query.username }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == "") {
            var jsonRespuestaIncorrecta = JSON.parse('{"exito":0}');
            response.send(jsonRespuestaIncorrecta);
        }
        else {
            var jsonRespuestaCorrecta = JSON.parse('{"exito":1}');
            response.send(jsonRespuestaCorrecta);
        }
    });
});

/******************************************************/
// Eliminar usuario
/******************************************************/
app.post("/eliminar-usuario", (request, response) => {
    collectionUsuarios.deleteOne({ "username":request.body.username }, (error, result) => {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            var jsonRespuestaIncorrecta = JSON.parse('{"exito":0}');
            response.send(jsonRespuestaIncorrecta);
        }
        else {
            var jsonRespuestaCorrecta = JSON.parse('{"exito":1}');
            response.send(jsonRespuestaCorrecta);
        }
    });
});

/******************************************************/
// Socios vinculados
/******************************************************/
app.post("/socios-vinculados", (request, response) => {
    collectionUsuarios.find({ "username": request.body.user_facilitador }, {"_id": 0, "sociosACargo": 1}).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No se encontró el array");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Socios no vinculados
/******************************************************/
app.post("/socios-no-vinculados", (request, response) => {
    collectionUsuarios.find({ "rol": "socio", "facilitadoresACargo": { "$not": { "$eq" : request.body.user_facilitador} } }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No se encontró el array");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Info usuario
/******************************************************/
app.post("/info-usuario", (request, response) => {
    collectionUsuarios.find({ "username": request.body.username }).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No se encontró el usuario");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Facilitadores a cargo
/******************************************************/
app.get("/facilitadores-a-cargo", (request, response) => {
    collectionUsuarios.find({ "username": request.body.user_socio }, {"_id": 0, "facilitadoresACargo": 1}).toArray(function (error, result) {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No se encontró el array");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Vincular facilitadores y usuarios
/******************************************************/
app.post("/vincular-socio", (request, response) => {
    collectionUsuarios.updateOne({ "username":request.body.user_facilitador }, {"$push": {"sociosACargo": request.body.user_socio} }, (error, result) => {
        if (error) {
            //return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No se encontró el socio");
        }
        else {
            response.send(result);
        }
    });

    collectionUsuarios.updateOne({ "username":request.body.user_socio }, {"$push": {"facilitadoresACargo": request.body.user_facilitador} }, (error, result) => {
        if (error) {
            //return response.status(500).send(error);
        }
        if (result == null) {
            response.send("No se encontró el facilitador");
        }
        else {
            response.send(result);
        }
    });
});

/******************************************************/
// Desvincular facilitadores y usuarios
/******************************************************/
app.post("/desvincular-socio", (request, response) => {
    collectionUsuarios.updateOne({ "username":request.body.user_facilitador }, {"$pull": {"sociosACargo": request.body.user_socio} }, (error, result) => {
        result.send("ok!");
    });

    collectionUsuarios.updateOne({ "username":request.body.user_socio }, {"$pull": {"facilitadoresACargo": request.body.user_facilitador} }, (error, result) => {
        result.send("ok!");
    });
});

/******************************************************/
// Crear grupo
/******************************************************/
app.post("/crear-grupo", (request, response) => {
    collectionGrupos.insertOne(request.body, (error, result) => {
        if(error) {
            return response.status(500).send(error);
        }
        if (result == null){
            response.send("Fallo en la creación");
        }
        else{    
            response.send("Creación completada");
        }
    });
});

/******************************************************/
// Eliminar grupo
/******************************************************/
app.post("/eliminar-grupo", (request, response) => {
    collectionGrupos.deleteOne({ "nombre": request.body.nombreGrupo, "facilitadorACargo": request.body.username }, (error, result) => {
        if (error) {
            return response.status(500).send(error);
        }
        if (result == null) {
            var jsonRespuestaIncorrecta = JSON.parse('{"exito":0}');
            response.send(jsonRespuestaIncorrecta);
        }
        else {
            var jsonRespuestaCorrecta = JSON.parse('{"exito":1}');
            response.send(jsonRespuestaCorrecta);
        }
    });
});