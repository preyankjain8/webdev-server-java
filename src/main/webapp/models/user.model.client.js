function User(username, password, firstName, lastName) {
  this.username = username;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  

  this.setUsername = setUsername;
  this.getUsername = getUsername;
  // ...same for rest of properties…

  function setUsername(username) {
    this.username = username;
  }
  function getUsername() {
    return this.username;
  }
  // ...same for rest of properties…
}
