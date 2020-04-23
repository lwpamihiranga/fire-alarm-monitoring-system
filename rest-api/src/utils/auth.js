const jwt = require('jsonwebtoken');

module.exports = (req, res, next) => {
  try {
    const token = req.headers.authorization;
    const user = jwt.verify(token, 'secret');
    req.userData = user;
    // console.log(user);
    if (user.email === 'admin@root.com') {
      next();
    } else {
      res.status(401).json({
        error: 'Unathorized',
      });
    }
  } catch (error) {
    return res.status(401).json({
      message: 'Unauthorized',
    });
  }
};
