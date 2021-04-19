$( "#province" ).change(function() {
  alert( "Handler for .change() called." );
});

$( document ).ready(function() {
  fetch("http://localhost:8080/api/v1/provinces").then(res => res.json()).then(json => {
    console.log(json);
  });
});
