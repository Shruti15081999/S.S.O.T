var request = new XMLHttpRequest()

function createUser() {
    document.write(document.getElementsByName("createUserForm").value)
    request.open('POST', 'http://localhost:5000/api/user/userDetails?email=' + document.getElementsByName("email").value);
    request.onload = function() {
        document.write(JSON.parse(this.response))
        document.write("hello")
    }
    request.send()
}