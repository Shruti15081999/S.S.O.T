var request = new XMLHttpRequest();
var response = null;

function validateemail(email) {
    var atposition = email.indexOf("@");
    var dotposition = email.lastIndexOf(".");
    if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length) {
        return false;
    }
    return true;
}

function login(email, password) {
    if (!validateemail(email)) {
        alert("Invalid email");
        return;
    }
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText);
            alert(this.responseText);
            sessionStorage.setItem('user', email);
            if (response.httpStatus == "OK") {
                userDetails();
            }
        }
    };
    request.open('POST', 'http://localhost:5000/api/user/login', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "email": email,
        "password": password
    };
    request.send(JSON.stringify(requestBody));
}

function logout() {
    sessionStorage.setItem("user", "");
    window.location.replace("../index.html");
}

function userDetails() {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            if (this.status == 200) {
                var user = response.user
                alert(response.message + " First Name: " + user.firstName + " Last Name: " + user.lastName);
                sessionStorage.setItem('userDetails', response.message);
            } else {
                alert(response.message);
            }
            window.location.replace("./home.html");
        }
    };
    request.open('GET', 'http://localhost:5000/api/user/userDetails?email=' + sessionStorage.getItem("user"), true);
    request.send();
}

function checkNotifications() {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            data = {
                message: response.message,
                notifications: response.notifications.notificationsList
            }
            console.log(data)
            alert(JSON.stringify(data));
        }
    };
    request.open('GET', 'http://localhost:5000/api/notification/userNotifications?email=' + sessionStorage.getItem("user"), true);
    request.send();
}

function updateStatus(location) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            alert(response.message);
            document.getElementById('updateStatusResponse').innerHTML = response.message;
        }
    };
    request.open('GET', 'http://localhost:5000/api/notification/updateStatus?email=' + sessionStorage.getItem("user") + '&location=' + location, true);
    request.send();
}

function deleteUser(email) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            alert(response.message);
        }
    };
    request.open('DELETE', 'http://localhost:5000/api/user/deleteUser', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "email": email
    };
    request.send(JSON.stringify(requestBody));
}

function createUser(firstName, lastName, email, location, password) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText);
            alert(response.message);
            logout();
        }
    };
    request.open('POST', 'http://localhost:5000/api/user/addUser', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "firstName": firstName,
        "lastName": lastName,
        "email": email,
        "password": password,
        "locationName": location
    };
    request.send(JSON.stringify(requestBody));
}

function addEmergencyContact(emergencyContactEmail) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText);
            alert(response.message);
        }
    };
    request.open('POST', 'http://localhost:5000/api/emergencycontact/addEmergencyContact', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "userEmail": sessionStorage.getItem("user"),
        "emergencyContactEmail": emergencyContactEmail
    };
    request.send(JSON.stringify(requestBody));
}