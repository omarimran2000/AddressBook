
function createNewBook() {
    $.ajax({
        url: "http://localhost:8080/addressBookRestCreate"
    }).then(function (data) {

        $('#console').empty();
        $('#console').append("The New Book ID is " + data.id);
    });

}

function createNewBuddy() {
    $.ajax({
        url: "http://localhost:8080/buddyInfoRestAdd",
        data: {
            name: $('#buddyName').val(),
            phone: $('#buddyPhone').val(),
            addr: $('#buddyAddr').val(),
            bookID: $('#buddyBook').val()
        }
    }).then(function (data) {
        $('#console').empty();
        $('#console').append(data.name + " has been added with ID: "+data.id);
    });
}
function deleteBuddy() {
    $.ajax({
        url: "http://localhost:8080/buddyInfoRestRemove",
        data: {
            name: $('#buddyName').val(),
            bookID: $('#buddyBook').val()
        }
    }).then(function (data) {
        $('#console').empty();
        $('#console').append(data.name + " has been deleted with ID: "+data.id);
    });
}
function displayBook() {
    $.ajax({
        url: "http://localhost:8080/addressBookRestDisplay",
        data: {
            bookID: $('#searchBook').val()
        }
    }).then(function (data) {
        $('#console').empty();
        $('#console').append("Address book with ID: "+data.id+ "<br>");
        let buddyString = "";

        for (let i=0;i<data.buddies.length;i++)
        {
            buddyString += "Name: " + data.buddies[i].name;
            buddyString += " Address: " + data.buddies[i].address;
            buddyString += " Phone: " + data.buddies[i].phone;
            buddyString += "<br>";
        }
        $('#console').append(buddyString);

    });
}