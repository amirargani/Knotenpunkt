function doGet(e) {
    var action = e.parameter.action;
    var token = e.parameter.token;
    if (action == "get" && token == "i5gi2MVVeE8Bk690jCTe@7abF") {
        return getData(e);
    }
}

function getData(e) {
    var currentdate = new Date();
    var day = currentdate.getDate();
    if (day > 0 && day < 10) {
        day = "0" + day;
    }
    var month = currentdate.getMonth() + 1;
    if (month > 0 && month < 10) {
        month = "0" + month;
    }
    var year = currentdate.getFullYear();
    var showfulldate = year + "-" + month + "-" + day;

    var cal = CalendarApp.getCalendarById("mail");
    var year = new Date().getFullYear();
    let Quarter = (new Date().getMonth() + 1).toString();
    if (Quarter >= 1 && Quarter <= 3) {
        var events = cal.getEvents(new Date("1/1/" + year.toString() + " 12:00 AM"), new Date("4/30/" + year.toString() + " 11:59 PM"));
    }
    if (Quarter >= 4 && Quarter <= 6) {
        var events = cal.getEvents(new Date("4/1/" + year.toString() + " 12:00 AM"), new Date("7/31/" + year.toString() + " 11:59 PM"));
    }
    if (Quarter >= 7 && Quarter <= 9) {
        var events = cal.getEvents(new Date("7/1/" + year.toString() + " 12:00 AM"), new Date("10/31/" + year.toString() + " 11:59 PM"));
    }
    if (Quarter >= 10 && Quarter <= 11) {
        var events = cal.getEvents(new Date("10/1/" + year.toString() + " 12:00 AM"), new Date("12/31/" + year.toString() + " 11:59 PM"));
    }
    if (Quarter == 12) {
        var events = cal.getEvents(new Date("12/1/" + year.toString() + " 12:00 AM"), new Date("1/31/" + (year + 1).toString() + " 11:59 PM"));
    }

    var records = {};
    data = [];

    for (var i = 0; i < events.length; i++) {
        var record = {};
        var title = events[i].getTitle();
        var description = events[i].getDescription();
        var startdate = events[i].getStartTime();
        var enddate = events[i].getEndTime();
        var location = events[i].getLocation();
        var showfullStartdatum = startdate.toString().substring(11, 15) + "-" + getMonth(startdate.toString().substring(4, 7)) + "-" + startdate.toString().substring(8, 10);
        if (showfullStartdatum >= showfulldate) {
            record["Startdatum"] = startdate.toString().substring(8, 10) + "." + getMonth(startdate.toString().substring(4, 7)) + "." + startdate.toString().substring(11, 15);
            record["Enddatum"] = enddate.toString().substring(8, 10) + "." + getMonth(enddate.toString().substring(4, 7)) + "." + enddate.toString().substring(11, 15);
            record["Startzeit"] = startdate.toString().substring(16, 21);
            record["Endzeit"] = enddate.toString().substring(16, 21);
            record["Titel"] = title;
            record["Beschreibung"] = description;
            record["Ort"] = location;
            data.push(record);
        }
    }
    records.itemsCalendar = data;
    var result = JSON.stringify(records);
    return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
}

function getMonth(month) {
    if ("Jan" == month) { month = "0" + 1; }
    if ("Feb" == month) { month = "0" + 2; }
    if ("Mar" == month) { month = "0" + 3; }
    if ("Apr" == month) { month = "0" + 4; }
    if ("May" == month) { month = "0" + 5; }
    if ("Jun" == month) { month = "0" + 6; }
    if ("Jul" == month) { month = "0" + 7; }
    if ("Aug" == month) { month = "0" + 8; }
    if ("Sep" == month) { month = "0" + 9; }
    if ("Oct" == month) { month = 10; }
    if ("Nov" == month) { month = 11; }
    if ("Dec" == month) { month = 12; }
    return month;
}