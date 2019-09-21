var map;
var posicionamentoMapa = true;
var marker;
var makers = new Array();
var mapOptions = {
    zoom: 15,
    mapTypeId: google.maps.MapTypeId.ROADMAP
};

function initialize() {
    // HTML 5
    var pos;
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {

            //ao entrar na tela, a aplicação resgata a latitude e longetude do usuário.
            $('#input-posicao').val(position.coords.latitude + "," + position.coords.longitude);
            $('#botao-salvar').click();

            var locals = new Array();
            for (i = 0; i < $('.get-posicao').length; i++) {
                locals.push($('.get-posicao')[i].childNodes[0].data);
            }

            //captura a localização atual.
            for (i = 0; i < locals.length; i++) {
                var posicao = locals[i].toString();
                var latitude = posicao.split(",")[0];
                var longetude = posicao.split(",")[1];

//pos = new google.maps.LatLng(position.coords.latitude,
//position.coords.longitude);


                //posiciona o mapa apenas uma vez
                if (posicionamentoMapa) {
                    pos = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
                    map.setCenter(pos);
                    posicionamentoMapa = false;
                }else{
                    pos = new google.maps.LatLng(latitude,
                        longetude);
                }

                    marker = new google.maps.Marker({
                    position: pos,
                    map: map,
                    icon: 'img/viatura_map.png',
                    title: 'Viatura: HZI-1001',
                    animation: google.maps.Animation.BOUNCE
                });
                makers.push(marker);
            }

        }, function () {
            handleNoGeolocation(true);
        });
    } else {
        // O browser não suporta o Geolocation
        handleNoGeolocation(false);
    }
    
        for(i=0;i< makers.length;i++){
            makers[i].setMap(null);
        }
        makers = new Array();
}

function handleNoGeolocation(errorFlag) {
    if (errorFlag) {
        var content = 'Desculpe, não foi possível encontrar a localização.';
    } else {
        var content = 'O seu browser não suporta este tipo de navegação.';
    }
    var options = {
        map: map,
        position: new google.maps.LatLng(60, 105),
        content: content
    };
    var infowindow = new google.maps.InfoWindow(options);
    map.setCenter(options.position);
}

google.maps.event.addDomListener(window, 'load', initialize);
