var sheetgoogle = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/...../edit");
var sheet = sheetgoogle.getSheetByName("Benutzer");

function doGet(e) {
    var action = e.parameter.action;
    if (action == "login") {
        return getLogin(e);
    }
}

function getLogin(e) {
    var nameEmail = e.parameter.email;
    var namePassword = e.parameter.password;
    
    var records = {};
    data = [];
    var rows = sheet.getRange(2, 1, 50, 5).getValues();
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i]
        var record = {};
        if (row[0] != "") {
            if (nameEmail == row[1] && namePassword == row[2]) {
                record["Email"] = row[1];
                record["Password"] = row[2];
                record["Firstname"] = row[3];
                record["Lastname"] = row[4];
                data.push(record);
                //records = data;
                records.itemsUser = data;
                var result = JSON.stringify(records);
                return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
            }
        }
    }
}