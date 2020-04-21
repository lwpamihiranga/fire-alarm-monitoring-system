const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const cors = require('cors');
const mongoose = require('mongoose');

const sensorRouter = require('./resources/sensor/sensor.router');

const app = express();

mongoose.connect('mongodb://localhost:27017/firealarm_db', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
});

app.use(morgan('dev'));
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors());

app.get('/', (req, res) => {
  res.send('working');
});

app.use('/sensor', sensorRouter);

app.listen(3000, () => {
  console.log('REST API started to listen on port 3000');
});
