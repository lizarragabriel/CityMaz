const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const morgan = require('morgan');

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
morgan.token('type', function (req, res) { return req.headers['content-type'] })
require('dotenv').config();

const port = process.env.PORT || 9000;
const mongoose = require('mongoose');

mongoose.connect("mongodb://127.0.0.1:27017",
    { useNewUrlParser: true, useUnifiedTopology: true}
)
.then(() => console.log('conectada'))
.catch(e => console.log(e))

// settings
app.set('view engine', 'ejs');
app.set('views', __dirname + '/views');

// middleware
app.use(express.json());
app.use(express.static(__dirname + "/public"));

//routes
app.use('/', require('./router/routes'))
app.use('/cities', require('./router/cities'));

app.use((req, res, next) => {
    res.status(404).render("404", {codigo: 404, descripcion: "no encontraste nada", tip: "suerte, crack"});
});

app.listen(port, () => {
    console.log(`corriendo en el puertox, ${port}`);
})