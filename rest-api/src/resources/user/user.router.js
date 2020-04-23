const express = require('express');
const router = express.Router();

const UserController = require('./user.controller');

router.post('/signup', UserController.signUp);

router.post('/login', UserController.login);

router.delete('/:id', UserController.deleteUser);

module.exports = router;
