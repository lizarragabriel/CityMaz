const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.render("index", {nombre: "gabriel", apellido: "rentería"});
});

module.exports = router;