
function createNewBook() {
    $.ajax({
        url: "/addressBookRestCreate"
    }).then(function (data) {

        $('#console').empty();
        $('#console').append("The New Book ID is " + data.id);
    });

}

function createNewBuddy() {
    $.ajax({
        url: "/buddyInfoRestAdd",
        data: {
            name: $('#buddyName').val(),
            phone: $('#buddyPhone').val(),
            addr: $('#buddyAddr').val(),
            city: $('#buddyCity').val(),
            bookID: $('#buddyBook').val()
        }
    }).then(function (data) {
        $('#console').empty();
        $('#console').append(data.name + " has been added with ID: "+data.id);
    });
}
function deleteBuddy() {
    $.ajax({
        url: "/buddyInfoRestRemove",
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
        url: "/addressBookRestDisplay",
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
            buddyString += " City: " + data.buddies[i].city;
            buddyString += "<br>";
        }
        $('#console').append(buddyString);

    });
}