var config = {
            apiKey: "AIzaSyDlrJm-UKrWYOeV9vh_y2-tlueVoq2PfzQ",
            authDomain: "hmif-7becb.firebaseapp.com",
            databaseURL: "https://hmif-7becb.firebaseio.com",
            projectId: "hmif-7becb",
            storageBucket: "hmif-7becb.appspot.com",
            messagingSenderId: "762580125700"
          };
          firebase.initializeApp(config);
var submitBtn = document.getElementById("submitBtn");
var btn = document.getElementById('file')
var selectedFile;

$(document).ready(function(){
  $("#submitBtn").hide();
  $(".slide").hide();
  //document.getElementById("file").addEventListener('change', handleFileSelect, false);
});


$('#file').on("change",function(event) {
  // body...
  selectedFile = event.target.files[0];
  $("#submitBtn").show();
});

function submitClick(){
  // body...
  var nopas = document.getElementById("nopas").value;
  var ketua = document.getElementById("ketua").value;
  var wakil = document.getElementById("wakil").value;
  var telp = document.getElementById("telp").value;
  var kandidatRef = firebase.database().ref('kandidat');
  // window.alert(nopas);
  window.alert(selectedFile);
  var filename = selectedFile.name;
  var storageRef = firebase.storage().ref('foto/' + filename)
  // var fileRef = storage().child(filename);
  window.alert(filename);
  //storageRef.put(selectedFile)

  //var storageRef = firebase.storage().ref('/images/' + filename);
  var uploadTask = storageRef.put(selectedFile);

  // Register three observers:
  // 1. 'state_changed' observer, called any time the state changes
  // 2. Error observer, called on failure
  // 3. Completion observer, called on successful completion
  uploadTask.on('state_changed', function(snapshot){
  // Observe state change events such as progress, pause, and resume
  // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
  }, function(error) {
  // Handle unsuccessful uploads
  }, function() {
  // Handle successful uploads on complete
  // For instance, get the download URL: https://firebasestorage.googleapis.com/...
  var downloadURL = uploadTask.snapshot.downloadURL;
  console.log(downloadURL);
  kandidatRef.child(nopas).set({
    "nopas": nopas,
    "ketua": ketua,
    "wakil": wakil,
    "telp": telp,
    "image": downloadURL
    });
  });

  
}