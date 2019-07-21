var request = new XMLHttpRequest();
var response = null;

function userDetails(email) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            if (this.status == 200) {
                var user = response.user
                alert(response.message + " First Name: " + user.firstName  + " Last Name: " + user.lastName);
            } else {
                alert(response.message);
            }
        }
    }; 
    request.open('GET', 'http://localhost:5000/api/user/userDetails?email=' + email, true);
    request.send();
}

function checkNotifications(email) {
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
    request.open('GET', 'http://localhost:5000/api/notification/userNotifications?email=' + email, true);
    request.send();
}

function updateStatus(email) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            alert(response.message);
        }
    }; 
    request.open('GET', 'http://localhost:5000/api/notification/updateStatus?email=' + email, true);
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

function addEmergencyContact(userEmail, emergencyContactEmail) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText);
            alert(response.message);
        }
    };
    request.open('POST', 'http://localhost:5000/api/emergencycontact/addEmergencyContact', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "userEmail": userEmail,
        "emergencyContactEmail": emergencyContactEmail
    };
    request.send(JSON.stringify(requestBody));
}

