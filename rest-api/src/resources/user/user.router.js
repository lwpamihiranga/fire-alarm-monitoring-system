const express = require('express');
const router = express.Router();

const UserController = require('./user.controller');

const checkAuth = require('../../utils/auth');

router.post('/signup', UserController.signUp);

router.post('/login', UserController.login);

router.delete('/:id', checkAuth, UserController.deleteUser);

module.exports = router;
