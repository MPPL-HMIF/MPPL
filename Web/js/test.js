var config = {
        apiKey: "AIzaSyDlrJm-UKrWYOeV9vh_y2-tlueVoq2PfzQ",
        authDomain: "hmif-7becb.firebaseapp.com",
        databaseURL: "https://hmif-7becb.firebaseio.com",
        projectId: "hmif-7becb",
        storageBucket: "hmif-7becb.appspot.com",
        messagingSenderId: "762580125700"
      };
      firebase.initializeApp(config);

var nim = document.getElementById("nim");
var nama = document.getElementById("name");
var email = document.getElementById("email");
var pass = document.getElementById("password");
var submitBtn = document.getElementById("submitBtn");
var firebaseRef = firebase.database().ref('users');


function submitClick() {
	// body...

	//firebaseRef.push().set(mainText);
	var inp_nim = nim.value;
	var inp_nama = nama.value;
	var inp_email = email.value;
	var inp_pass = pass.value;
	// window.alert(inp_nim+inp_email);
	// window.alert(email);
	// window.alert(pass);
	firebaseRef.child(inp_nim).set({
    "nama": inp_nama,
    "email": inp_email,
    "password": "inp_pass"
  	});
}