const express = require('express');
const router = express.Router();

const SensorController = require('./sensor.controller');

const checkAuth = require('../../utils/auth');

router.get('/', SensorController.getAll);

router.get('/:id', SensorController.getOne);

router.post('/', checkAuth, SensorController.createOne);

router.patch('/:id', checkAuth, SensorController.updateOne);

router.put('/:id', SensorController.updateOneByPut);

router.delete('/:id', checkAuth, SensorController.deleteOne);

module.exports = router;
