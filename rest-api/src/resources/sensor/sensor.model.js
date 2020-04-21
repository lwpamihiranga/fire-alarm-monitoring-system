const mongoose = require('mongoose');

const sensorSchema = mongoose.schema({
  _id: mongoose.Schema.Types.ObjectId,
  isActive: Boolean,
  location: { roomNo: Number, floor: Number },
  smokeLevel: Number,
  co2Level: Number,
});

module.exports = mongoose.model('Sensor', sensorSchema);
