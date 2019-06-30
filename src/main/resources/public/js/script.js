var request = new XMLHttpRequest();
var response = null;

function userDetails(email) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            if (this.status == 200) {
                var user = response.user
                document.getElementById('userDetailsResponseField').innerHTML = response.message
                + " First Name: " + user.firstName
                + " Last Name: " + user.lastName;
            } else {
                document.getElementById('userDetailsResponseField').innerHTML = response.message;
            }
        }
    }; 
    request.open('GET', 'http://localhost:5000/api/user/userDetails?email=' + email, true);
    request.send();
}

function deleteUser(email) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            document.getElementById('deleteUserResponseField').innerHTML = response.message
        }
    }; 
    request.open('DELETE', 'http://localhost:5000/api/user/deleteUser', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "email": email
    };
    request.send(JSON.stringify(requestBody));
}

function createUser(firstName, lastName, email, password) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            document.getElementById('createUserResponseField').innerHTML = response.message;
        }
    };
    request.open('POST', 'http://localhost:5000/api/user/addUser', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "firstName": firstName,
        "lastName": lastName,
        "email": email,
        "password": password
    };
    request.send(JSON.stringify(requestBody));
}

