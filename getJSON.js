var sheetgoogle = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/...../edit");
let Quarter = (new Date().getMonth() + 1);
if (Quarter >= 1 && Quarter <= 3) {
    var sheet = sheetgoogle.getSheetByName("1.Quartal " + new Date().getFullYear());
    var sheetnext = "";
    if (Quarter == 3) {
        var sheetnext = sheetgoogle.getSheetByName("2.Quartal " + new Date().getFullYear());
        var showtabledatenext = new Date().getFullYear() + "-" + "05" + "-" + "10";
    }
}
if (Quarter >= 4 && Quarter <= 6) {
    var sheet = sheetgoogle.getSheetByName("2.Quartal " + new Date().getFullYear());
    var sheetnext = "";
    if (Quarter == 6) {
        var sheetnext = sheetgoogle.getSheetByName("3.Quartal " + new Date().getFullYear());
        var showtabledatenext = new Date().getFullYear() + "-" + "08" + "-" + "10";
    }
}
if (Quarter >= 7 && Quarter <= 9) {
    var sheet = sheetgoogle.getSheetByName("3.Quartal " + new Date().getFullYear());
    var sheetnext = "";
    if (Quarter == 9) {
        var sheetnext = sheetgoogle.getSheetByName("4.Quartal " + new Date().getFullYear());
        var showtabledatenext = new Date().getFullYear() + "-" + "11" + "-" + "10";
    }
}
if (Quarter >= 10 && Quarter <= 12) {
    var sheet = sheetgoogle.getSheetByName("4.Quartal " + new Date().getFullYear());
    var sheetnext = "";
    if (Quarter == 12) {
        var sheetnext = sheetgoogle.getSheetByName("1.Quartal " + new Date().getFullYear() + 1);
        var showtabledatenext = new Date().getFullYear() + 1 + "-" + "02" + "-" + "10";
    }
}

function doGet(e) {
    var action = e.parameter.action;
    var token = e.parameter.token;
    if (action == "get" && token == "o04TR4dO4KeY*q6@lnXM$4@V@") {
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
    var showdate = year + "-" + month + "-" + day;
    var records = {};
    data = [];
    datanext = [];
    var rows = sheet.getRange(4, 1, 15, 10).getValues(); //11
    if (sheetnext != "") {
        var rowsnext = sheetnext.getRange(4, 1, 15, 10).getValues(); //11
    }
    else {
        var rowsnext = "";
    }
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var record = {};
        if (row[0] != "") {
            var showtabledate = row[0].substring(6, 10) + "-" + row[0].substring(3, 5) + "-" + row[0].substring(0, 2);
            if (showtabledate >= showdate) {
                record["Datum"] = row[0];
                record["Fr_Sabbat"] = row[1];
                record["Moderation"] = row[2];
                record["KidsGo"] = row[3];
                record["Gespreachsltng"] = row[4];
                record["Predigt"] = row[5];
                record["Kindermoment"] = row[6];
                record["Zeit"] = row[7];
                record["Ort"] = row[8];
                record["Putzdienst"] = row[9];
                // Music is deleted.
                //record["Musik"] = row[4];
                //record["Gespreachsltng"] = row[5];
                //record["Predigt"] = row[6];
                //record["Kindermoment"] = row[7];
                //record["Zeit"] = row[8];
                //record["Ort"] = row[9];
                //record["Putzdienst"] = row[10];
                data.push(record);
            }
        }
    }
    if (rowsnext != "") {
        for (var i = 0; i < rowsnext.length; i++) {
            var row = rowsnext[i];
            var record = {};
            if (row[0] != "") {
                var showtabledate = row[0].substring(6, 10) + "-" + row[0].substring(3, 5) + "-" + row[0].substring(0, 2);
                if (showtabledate >= showdate && showtabledate <= showtabledatenext) {
                    record["Datum"] = row[0];
                    record["Fr_Sabbat"] = row[1];
                    record["Moderation"] = row[2];
                    record["KidsGo"] = row[3];
                    record["Gespreachsltng"] = row[4];
                    record["Predigt"] = row[5];
                    record["Kindermoment"] = row[6];
                    record["Zeit"] = row[7];
                    record["Ort"] = row[8];
                    record["Putzdienst"] = row[9];
                    // Music is deleted.
                    //record["Musik"] = row[4];
                    //record["Gespreachsltng"] = row[5];
                    //record["Predigt"] = row[6];
                    //record["Kindermoment"] = row[7];
                    //record["Zeit"] = row[8];
                    //record["Ort"] = row[9];
                    //record["Putzdienst"] = row[10];
                    datanext.push(record);
                }
            }
        }
    }
    if (rowsnext != "") {
        var union = [...new Set([...data, ...datanext])];
        records.items = union;
    }
    else {
        records.items = data;
    }
    var result = JSON.stringify(records);
    Logger.log(result);
    return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.JSON);
}