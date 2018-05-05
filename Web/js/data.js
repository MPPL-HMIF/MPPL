var config = {
        apiKey: "AIzaSyDlrJm-UKrWYOeV9vh_y2-tlueVoq2PfzQ",
        authDomain: "hmif-7becb.firebaseapp.com",
        databaseURL: "https://hmif-7becb.firebaseio.com",
        projectId: "hmif-7becb",
        storageBucket: "hmif-7becb.appspot.com",
        messagingSenderId: "762580125700"
      };
      firebase.initializeApp(config);

// $(document).ready(function(){

// 	var rootRef = firebase.database().ref().child("Users");
// 	rootRef.on("child_added", snap => {
// 		alert(snap.val());
// 	});

// });


// var tblUsers = document.getElementById('tbl_user_list');
  var databaseRef = firebase.database().ref().child("users");
// databaseRef.on('value', gotData, errData)
// var rowIndex = 1;

// databaseRef.once('value',function(snapshot){
// 	snapshot.forEach(function(childSnapshot){
// 		var childKey = childSnapshot.key;
// 		var childData = childSnapshot.val();

// 		var row = tblUsers.insertRow(rowIndex);
// 		var cellId = row.insertCell(0);
// 		var cellName = row.insertCell(1);
// 		cellId.appendChild(document.createTextNode(childKey));
// 		cellName.appendChild(document.createTextNode(childData));

// 		rowIndex = rowIndex + 1;
// 	});
// });


databaseRef.on("child_added", snap => {
	//alert(snap.val());
	//var childKey = childSnapshot.key;
	var nama = snap.child("nama").val();
	var email = snap.child("email").val();
	var password = snap.child("password").val();		

	$("#tbl_body").append("<tr><td>"+ "nim" +"</td><td>"+ nama +"</td><td>"+ email +"</td><td>"+ password +"</td></tr>");
});


//kodingan baru
// function gotData(data) {
// 	// body...
// 	//console.log(data.val());
// 	var users  = data.val();
// 	var keys = Object.keys(users);
// 	console.log(keys);
// 	for (var i = 0; i < keys.length; i++){
// 		var k = keys[i];
// 		var initials = users[k].initials;
// 		var users = users[k].users;
// 		console.log(initials, users);
// 		// var td = createElement('td', initials + ': ' + users);
// 		// td.parent('tbl_user_list');
// 	}
// }

// function errData(err) {
// 	// body...
// 	console.log('Error!');
// 	console.log(err);
// }


//baru pasang

// databaseRef.on("child_added", snap => {
// 	//alert(snap.val());
// 	//var childKey = childSnapshot.key;
// 	var nama = snap.child("nama").val();
// 	var email = snap.child("email").val();
// 	var password = snap.child("password").val();		

// 	$("#tbl_body").append("<tr><td>"+ "nim" +"</td><td>"+ nama +"</td><td>"+ email +"</td><td>"+ password +"</td></tr>");
// });
