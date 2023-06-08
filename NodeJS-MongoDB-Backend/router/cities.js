const express = require('express');
const router = express.Router();
const City = require('../models/city');

router.get('/', async (req, res) => {
    try{
        const arrayEmployeesDB = await City.find()
        if(arrayEmployeesDB == 0){
            res.render("cities", {
                empty: true
            })
        }else {
            res.render("cities", {
                arrayEmployeesDB,
                empty: false
            })
        }
    } catch(error){
        console.log(error)
    }
});

router.get('/all', async (req, res) => {
    try{
        console.log("loading cities")
        const arrayEmployeesDB = await City.find()
        res.json(arrayEmployeesDB)
    } catch(error){
        console.log(error)
    }
});

router.post('/', async (req, res) => {
    const body = req.body;
    try {
        console.log("POST METHOD")
        const employeeDB = new City(body)
        await employeeDB.save();
        res.redirect('/cities');
        

    } catch(err) {
        console.log(err);
    }
});

router.get('/:id', async (req, res) => {  
    const id = req.params.id;
    try {
        const employeeDB = await City.findOne({_id: id})
        res.render('edit', {
            employeeDB: employeeDB,
            error: false
        })
    } catch(err) {
        res.render('edit', {
            error: true,
            mensaje: "Not Found"
        })
    }
})

router.get('/all/:id', async (req, res) => {  
    const id = req.params.id;
    try {
        const employeeDB = await City.findOne({_id: id})
        res.json(employeeDB)
    } catch(err) {
        console.log("ERROR")
    }
})

router.delete('/:id', async (req, res) => {
    const id = req.params.id;
    try {
        const employeeDB = await City.findByIdAndDelete({_id: id})
        if(employeeDB) {
            res.json({
                estado: true,
                mensaje: 'Deleted'
                
            })
            console.log('Success')
        } else {
            res.json({
                estado: false,
                mensaje: 'Error'
            })
        }
    } catch (error) {
        console.log(error);
    }
});


router.put('/:id', async (req, res) => {
    const id = req.params.id
    const body = req.body
    try {
        const employeeDB = await City.findByIdAndUpdate(id, body, { useFindAndModify: false })
        console.log('Updated')
        res.json({
            estado: true,
            mensaje: 'Uptated'
        })
    } catch (error) {
        console.log(error)
        
        res.json({
            estado: false,
            mensaje: 'Failed'
        })
    }
})

module.exports = router;