const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const employeeSchema = new Schema({
    firstName: String,
    lastName: String,
    email: String,
}, {
    versionKey: false
})


// crear modelo

const Employee = mongoose.model('Employee', employeeSchema);

module.exports = Employee;