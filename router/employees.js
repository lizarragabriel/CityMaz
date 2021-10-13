const express = require('express');
const router = express.Router();

const Employee = require('../models/employee');

router.get('/', async (req, res) => {
    try{
        const arrayEmployeesDB = await Employee.find()
        const prueba = Object.values(arrayEmployeesDB);
        if(arrayEmployeesDB == 0){
            res.render("employees", {
                empty: true
            })
        }else {
            res.render("employees", {
                arrayEmployeesDB,
                empty: false
            })
        }
    } catch(error){
        
    }
});

router.post('/', async (req, res) => {
    const body = req.body;
    try {
        const employeeDB = new Employee(body)
        await employeeDB.save();
        res.redirect('/employees');
        

    } catch(err) {
        console.log(err);
    }
});

router.get('/:id', async (req, res) => {  
    const id = req.params.id;
    try {
        const employeeDB = await Employee.findOne({_id: id})
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

router.delete('/:id', async (req, res) => {
    const id = req.params.id;
    try {
        const employeeDB = await Employee.findByIdAndDelete({_id: id})
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
        const employeeDB = await Employee.findByIdAndUpdate(id, body, { useFindAndModify: false })
        console.log('Updated')
        res.json({
            estado: true,
            mensaje: 'Uptated'
        })
        
    } catch (error) {
        console.log(error)
        
        res.json({
            estado: false,
            mensaje: 'Fallamos!!'
        })
    }
})

module.exports = router;