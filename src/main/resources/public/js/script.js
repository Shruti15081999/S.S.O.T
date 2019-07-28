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
            sessionStorage.setItem('user', email);
            if (response.httpStatus == "OK") {
                window.location.replace("./home.html");
            } else {
                alert(response.message);
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
                sessionStorage.setItem('userDetails', response.user);
                document.getElementById('firstName').value = response.user.firstName;
                document.getElementById('lastName').value = response.user.lastName;
                document.getElementById('email').value = response.user.email;
                document.getElementById('addressLine1').value = response.user.address.addressLine1;
                document.getElementById('addressLine2').value = response.user.address.addressLine2;
                document.getElementById('city').value = response.user.address.city;
                document.getElementById('state').value = response.user.address.state;
                document.getElementById('country').value = response.user.address.country;
            } else {
                alert(response.message);
            }
        }
    };
    request.open('GET', 'http://localhost:5000/api/user/userDetails?email=' + sessionStorage.getItem("user"), true);
    request.send();
}

function checkNotifications() {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText)
            notifications = response.notifications.notificationsList
            console.log(notifications)
            let table = ""
            notifications.forEach(notification => {
                table += "<tr>"
                    + "<td>" + notification.message + "</td>"
                    + "</tr>"
            });
            document.getElementById('notificationTable').innerHTML = table;
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

function updateUser(firstName, lastName, email, addressLine1, addressLine2, city, state, country) {
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            response = JSON.parse(this.responseText);
            alert(response.message);
        }
    };
    request.open('POST', 'http://localhost:5000/api/user/editUser', true);
    request.setRequestHeader("Content-Type", "application/json");
    var requestBody = {
        "firstName": firstName,
        "lastName": lastName,
        "email": email,
        "address": {
            "addressLine1": addressLine1,
            "addressLine2": addressLine2,
            "city": city,
            "state": state,
            "country": country
        }
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