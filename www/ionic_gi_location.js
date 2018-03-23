// var exec = require('cordova/exec');
function gi_location(){

}

gi_location.prototype.getLatAndLong= function (successCallback, errorCallback) {
  console.log("test callig get geocode method")
  cordova.exec(successCallback, errorCallback, "ionic_gi_location", "get_location_values", []);
};
/*gi_location.prototype.toast= function (successCallback, errorCallback) {
  console.log("test callig toast method")
  cordova.exec(successCallback, errorCallback, "ionic_gi_location", "toast", []);
};*/
gi_location.prototype.check_gps= function (successCallback, errorCallback) {
  console.log("test callig Check Gps method")
  cordova.exec(successCallback, errorCallback, "ionic_gi_location", "check_gps", []);
};



gi_location.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.gi_location = new gi_location();
  return window.plugins.gi_location;
};
cordova.addConstructor(gi_location.install);
