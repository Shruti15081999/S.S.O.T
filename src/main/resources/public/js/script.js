var request = new XMLHttpRequest()

function userDetails(form) {
    request.open('GET', 'http://localhost:5000/api/user/userDetails?email=' + form.email.value);
    request.onload = function() {
        // response = JSON.parse(this.response)
        document.getElementByid('responseField').innerHTML = JSON.parse(this.response)
        // document.write("\nhello")
    }
    request.send()
}

function createUser(form) {
    request.open('POST', 'http://localhost:5000/api/user/addUser');
    request.setRequestHeader("Content-Type", "application/json")
    request.onload = function() {
        document.write(JSON.parse(this.response))
        document.write("\nhello")
    }
    var requestBody = {
        "firstName": form.firstName.value,
        "lastName": form.lastName.value,
        "email": form.email.value,
        "password": form.password.value
    }
    console.log(requestBody)
    request.send({requestBody})
}

