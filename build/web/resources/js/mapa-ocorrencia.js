var directionDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize() {
    directionsDisplay = new google.maps.DirectionsRenderer();
    var myLatlng = new google.maps.LatLng(-10.673888, -37.46814);

    var myOptions = {
        zoom: 7,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: myLatlng
    };

    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
    directionsDisplay.setMap(map);
    directionsDisplay.setPanel(document.getElementById("directionsPanel"));
}

function calcRoute() {
    var start = document.getElementById("origem").value;
    var end = document.getElementById('destino').value;

    //verifica se os campos foram preenchidos.
    if (start == '' || end == '') {
        alert('Preencha os campos!');
        return;
    }

    var request = {
        origin: start,
        destination: end,
        travelMode: google.maps.DirectionsTravelMode.DRIVING
    };

    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
            document.getElementById('direcao').style.visibility = 'visible';
        } else {
            alert("Rota desconhecida!");
        }
    });
}