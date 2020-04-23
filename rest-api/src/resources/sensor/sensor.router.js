const express = require('express');
const router = express.Router();

const SensorController = require('./sensor.controller');

router.get('/', SensorController.getAll);

router.get('/:id', SensorController.getOne);

router.post('/', SensorController.createOne);

router.patch('/:id', SensorController.updateOne);

router.delete('/:id', SensorController.deleteOne);

module.exports = router;
