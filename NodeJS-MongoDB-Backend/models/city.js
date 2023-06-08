const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const employeeSchema = new Schema({
    name: String,
    image: String,
    description: String,
}, {
    versionKey: false
})

const City = mongoose.model('Employee', employeeSchema);
module.exports = City;