const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');

const Sensor = require('./sensor.model');

router.get('/', (req, res) => {
  Sensor.find()
    .exec()
    .then((docs) => {
      res.status(200).json(docs);
    })
    .catch((error) => {
      res.status(500).json({
        error: {
          message: 'Unable to find',
          error: error,
        },
      });
    });
});

router.get('/:id', (req, res) => {
  const id = req.params.id;

  Sensor.findById(id)
    .exec()
    .then((doc) => {
      console.log(doc);
      if (doc) {
        res.status(200).json({
          message: doc,
        });
      } else {
        res.status(404).json({
          error: {
            message: 'Unable to found',
          },
        });
      }
    })
    .catch((error) => {
      console.log(error);

      res.status(500).json({ error: error });
    });
});

router.post('/', (req, res) => {
  const sensor = new Sensor({
    _id: new mongoose.Types.ObjectId(),
    isActive: req.body.isActive,
    location: req.body.location,
    smokeLevel: req.body.smokeLevel,
    co2Level: req.body.co2Level,
  });

  sensor
    .save()
    .then((result) => {
      console.log(result);
      res.status(201).json({
        message: 'Sensor entry created',
        created: result,
      });
    })
    .catch((error) => {
      console.log(error);
      res.status(400).json({ error: error });
    });
});

router.patch('/:id', (req, res) => {
  const id = req.params.id;

  const updateOps = {};

  for (const ops of req.body) {
    updateOps[ops.propName] = ops.value;
  }

  Sensor.update({ _id: id }, { $set: updateOps })
    .exec()
    .then((result) => {
      res.status(200).json(result);
    })
    .catch((error) => {
      res.status(400).json({
        error: error,
      });
    });
});

router.delete('/:id', (req, res) => {
  const id = req.params.id;

  Sensor.remove({ _id: id })
    .exec()
    .then((result) => {
      res.status(200).json({ result });
    })
    .catch((error) => {
      res.status(404).json({
        error: error,
      });
    });
});

module.exports = router;
