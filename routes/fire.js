
var firebase = require("firebase");

var config = {
    apiKey: "AIzaSyD5eEpYb3ZaM8kSYjJzEJaRZ7qps-7pM-I",
    authDomain: "batik-webservices.firebaseapp.com",
    projectId: "batik-webservices",
    storageBucket: "batik-webservices.appspot.com",
    messagingSenderId: "354662656620",
    appId: "1:354662656620:web:1bfcf6e79cb7200fbfc649"
}
var fire = firebase.initializeApp(config);
module.exports = fire
