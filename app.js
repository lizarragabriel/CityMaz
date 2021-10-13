const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const morgan = require('morgan');

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
morgan.token('type', function (req, res) { return req.headers['content-type'] })
require('dotenv').config();

const port = process.env.PORT || 9000;
//const port = process.env.PORT; // variable de entorno -  de heroku
const mongoose = require('mongoose');

mongoose.connect(process.env.MONGO_URI,
    { useNewUrlParser: true, useUnifiedTopology: true}
)
.then(() => console.log('conectada'))
.catch(e => console.log(e))


// settings
app.set('view engine', 'ejs'); // motor de plantilla
app.set('views', __dirname + '/views'); // motor de plantilla

// middleware
app.use(express.json());
app.use(express.static(__dirname + "/public")); // directorio principal

app.use('/', require('./router/routes')) // exporta las rutas
app.use('/employees', require('./router/employees'));

app.use((req, res, next) => {
    //res.status(404).sendFile(__dirname + "/public/404.html");
    res.status(404).render("404", {codigo: 404, descripcion: "no encontraste nada", tip: "suerte, crack"});
});

// escuchando
app.listen(port, () => {
    console.log(`corriendo en el puertox, ${port}`);
})