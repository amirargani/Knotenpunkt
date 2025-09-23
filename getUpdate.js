var sheetgoogle = SpreadsheetApp.openByUrl("https://docs.google.com/spreadsheets/d/...../edit");
let QuarterMonth = (new Date().getMonth() + 1);
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
    if (action == "update") {
        return updateData(e);
    }
}

function updateData(e) {
    var id = e.parameter.Datum;
    var nameFr_Sabbat = e.parameter.Fr_Sabbat;
    var nameModeration = e.parameter.Moderation;
    var nameKidsGo = e.parameter.KidsGo;
    //var nameMusik = e.parameter.Musik;
    var nameGespreachsltng = e.parameter.Gespreachsltng;
    var namePredigt = e.parameter.Predigt;
    var nameKindermoment = e.parameter.Kindermoment;
    var namePutzdienst = e.parameter.Putzdienst;
    var flag = 0;
    var flagnext = 0;
    var rows = sheet.getRange(4, 1, 15, 10).getValues(); //11
    if (sheetnext != "") {
        var rowsnext = sheetnext.getRange(4, 1, 15, 10).getValues(); //11
    }
    else {
        var rowsnext = "";
    }
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i]
          var showtabledate = row[0].substring(6, 10) + "-" + row[0].substring(3, 5) + "-" + row[0].substring(0, 2);
          var showdate = id.substring(6, 10) + "-" + id.substring(3, 5) + "-" + id.substring(0, 2);
          if (showtabledate == showdate) {
            flag = 1;
            sheet.getRange(i + 4, 2).setValue(nameFr_Sabbat);
            sheet.getRange(i + 4, 3).setValue(nameModeration);
            sheet.getRange(i + 4, 4).setValue(nameKidsGo);
            sheet.getRange(i + 4, 5).setValue(nameGespreachsltng);
            sheet.getRange(i + 4, 6).setValue(namePredigt);
            sheet.getRange(i + 4, 7).setValue(nameKindermoment);
            sheet.getRange(i + 4, 10).setValue(namePutzdienst);
            //sheet.getRange(i + 4, 5).setValue(nameMusik);
            //sheet.getRange(i + 4, 6).setValue(nameGespreachsltng);
            //sheet.getRange(i + 4, 7).setValue(namePredigt);
            //sheet.getRange(i + 4, 8).setValue(nameKindermoment);
            //sheet.getRange(i + 4, 11).setValue(namePutzdienst);
          }
          if (flag == 1) {
              var result = "The update was successful.";
          }
      }
      if (rowsnext != "") {
        for (var i = 0; i < rowsnext.length; i++) {
            var row = rowsnext[i]
              var showtabledate = row[0].substring(6, 10) + "-" + row[0].substring(3, 5) + "-" + row[0].substring(0, 2);
              var showdate = id.substring(6, 10) + "-" + id.substring(3, 5) + "-" + id.substring(0, 2);
              if (showtabledate == showdate && showtabledate <= showtabledatenext) {
                flagnext = 1;
                  sheetnext.getRange(i + 4, 2).setValue(nameFr_Sabbat);
                  sheetnext.getRange(i + 4, 3).setValue(nameModeration);
                  sheetnext.getRange(i + 4, 4).setValue(nameKidsGo);
                  sheetnext.getRange(i + 4, 5).setValue(nameGespreachsltng);
                  sheetnext.getRange(i + 4, 6).setValue(namePredigt);
                  sheetnext.getRange(i + 4, 7).setValue(nameKindermoment);
                  sheetnext.getRange(i + 4, 10).setValue(namePutzdienst);
                  //sheetnext.getRange(i + 4, 5).setValue(nameMusik);
                  //sheetnext.getRange(i + 4, 6).setValue(nameGespreachsltng);
                  //sheetnext.getRange(i + 4, 7).setValue(namePredigt);
                  //sheetnext.getRange(i + 4, 8).setValue(nameKindermoment);
                  //sheetnext.getRange(i + 4, 11).setValue(namePutzdienst);
              }
              if (flagnext == 1) {
                  var result = "The update was successful.";
              }
          }
    }
    if (rowsnext != "") {
        if (flag == 0 && flagnext == 0) {
            var result = "Update failed.";
          }
    }
    else {
        if (flag == 0) {
            var result = "Update failed.";
          }
    }
    return ContentService.createTextOutput(result).setMimeType(ContentService.MimeType.TEXT);
}
