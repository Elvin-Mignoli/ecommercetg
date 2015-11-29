<%-- 
    Document   : Channel1
    Created on : 14/09/2015, 22:14:31
    Author     : Elvin
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Vídeo Chat</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
        
        <link rel="author" type="text/html" href="https://plus.google.com/+MuazKhan">
        <meta name="author" content="Muaz Khan">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/style.css" rel="stylesheet" type="text/css"/>
        <link href="../../js/libs/sweet-notify/sweetalert.css" rel="stylesheet" type="text/css"/>
        <style>
            audio, video {
                -moz-transition: all 1s ease;
                -ms-transition: all 1s ease;
                
                -o-transition: all 1s ease;
                -webkit-transition: all 1s ease;
                transition: all 1s ease;
                vertical-align: top;
                width: 50%;
                
            }
        </style>
        <script>
            document.createElement('article');
            document.createElement('footer');
        </script>
        
        <!-- scripts used for broadcasting -->
        <script src="//cdn.webrtc-experiment.com/firebase.js"> </script> <!-- Signaling -->
        <script src="//cdn.webrtc-experiment.com/RTCPeerConnection-v1.5.js"> </script> <!-- WebRTC simple wrapper -->
        <script src="//cdn.webrtc-experiment.com/webrtc-broadcasting/broadcast.js"> </script> <!-- Multi-user connectivity handler -->
        
        <!-- This Library is used to detect WebRTC features -->
        <script src="//cdn.webrtc-experiment.com/DetectRTC.js"></script>
    </head>

    <body>
        <!--Navbar -->
        <nav class="navbar navbar-inverse">
          <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
              </button>
              <a class="navbar-brand" href="#">Know-How</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
              <ul class="nav navbar-nav">
                  <li class="active"><a href="#" onclick="homechat('prestador')">Home</a></li>
               
              </ul>
              <ul class="nav navbar-nav navbar-right">
                  <li><a href="#" onclick="logoffchat()"><span class="glyphicon glyphicon-log-in" id='logoff'></span> Sair</a></li>
              </ul>
            </div>
          </div>
        </nav><!--fim Navbar -->
       
        <!-- Body Web Broadcasting-->
        <div class="container-fluid col-lg-12">
            <!-- copy this <section> and next <script> -->
            <div class="row">
                <div class="form-group col-lg-2">
                    <select id="broadcasting-option" class="form-control ">
                        <option>Audio + Video</option>
                        <option>Only Audio</option>
                    </select>
                </div>
                <div  class=" form-group col-lg-7">
                    <input  class="form-control " type="text" placeholder="Digite seu nome" id="broadcast-name" value="${sessionScope.user.nome} ${sessionScope.user.sobrenome}">
                </div>
                <div  class=" form-group col-lg-2">
                    <button id="setup-new-broadcast" class="btn  btn-info setup ">Criar a sala de videoconferência</button>
                </div>
                <br>
            </div> 
                   
            <!-- list of all available broadcasting rooms -->
            <table class="table-responsive"  id="rooms-list"></table>

            <!-- local/remote videos container -->
            <div id="videos-container"></div>   
        </div> 
        <input type="hidden" name='idCliente' id="idCliente" value="${requestScope.notify.idCliente}"/>
        <input type="hidden" name='idPrestador'id="idPrestador" value="${requestScope.notify.idPrestador}"/>
        <input type="hidden" name='idPedido' id="idPedido" value="${requestScope.notify.pedido.id}"/>
        <input type="hidden" name='Canal' id="canal" value="${requestScope.notify.channelChat}"/>
        <script>
            // Muaz Khan     - https://github.com/muaz-khan
            // MIT License   - https://www.webrtc-experiment.com/licence/
            // Documentation - https://github.com/muaz-khan/WebRTC-Experiment/tree/master/webrtc-broadcasting
           //salvar notify
           
       
           //config socket
           var config = {
                openSocket: function(config) {
                    // https://github.com/muaz-khan/WebRTC-Experiment/blob/master/Signaling.md
                    // This method "openSocket" can be defined in HTML page
                    // to use any signaling gateway either XHR-Long-Polling or SIP/XMPP or WebSockets/Socket.io
                    // or WebSync/SignalR or existing implementations like signalmaster/peerserver or sockjs etc.

                    var channel = config.channel || ${param.canal};
                    
                    //alert(channel);
                    var socket = new Firebase('https://webrtc.firebaseIO.com/' + channel);
                    socket.channel = channel;
                    socket.on("child_added", function(data) {
                        config.onmessage && config.onmessage(data.val());
                    });
                    socket.send = function(data) {
                        this.push(data);
                    };
                    config.onopen && setTimeout(config.onopen, 1);
                    socket.onDisconnect().remove();
                    return socket;
                },
                onRemoteStream: function(htmlElement) {
                    htmlElement.setAttribute('controls', true);
                    videosContainer.insertBefore(htmlElement, videosContainer.firstChild);
                    htmlElement.play();
                    rotateInCircle(htmlElement);
                },
                onRoomFound: function(room) {
                    var alreadyExist = document.querySelector('button[data-broadcaster="' + room.broadcaster + '"]');
                    if (alreadyExist) return;

                    if (typeof roomsList === 'undefined') roomsList = document.body;

                    var tr = document.createElement('tr');
                    tr.innerHTML = '<td><strong>' + room.roomName + '</strong>  iniciou uma sala de Videoconferência!</td>' +
                        '<td><button class="join">Entrar na sala</button></td>';
                    roomsList.insertBefore(tr, roomsList.firstChild);
                    $('#setup-new-broadcast').text("Ligar WebCam/Dispositivos compatíveis").attr({title:"Teste"});
                    var joinRoomButton = tr.querySelector('.join');
                    joinRoomButton.setAttribute('data-broadcaster', room.broadcaster);
                    joinRoomButton.setAttribute('data-roomToken', room.broadcaster);
                    joinRoomButton.disabled = true;
                    joinRoomButton.setAttribute('class', 'btn btn-info');
                    $('#setup-new-broadcast').on("click",function (){
                        joinRoomButton.disabled = false;
                    });
                       
                    
                    joinRoomButton.onclick = function() {
                        this.disabled = true;

                        var broadcaster = this.getAttribute('data-broadcaster');
                        var roomToken = this.getAttribute('data-roomToken');
                        broadcastUI.joinRoom({
                            roomToken: roomToken,
                            joinUser: broadcaster
                        });
                        hideUnnecessaryStuff();
                    };
                     
                },
                onNewParticipant: function(numberOfViewers) {
                    document.title = 'Viewers: ' + numberOfViewers;
                }
            };

            function setupNewBroadcastButtonClickHandler() {
                document.getElementById('broadcast-name').disabled = true;
                document.getElementById('setup-new-broadcast').disabled = true;

                captureUserMedia(function() {
                    var shared = 'video';
                    if (window.option == 'Only Audio') {
                        shared = 'audio';
                    }
                    if (window.option == 'Screen') {
                        shared = 'screen';
                    }

                    broadcastUI.createRoom({
                        roomName: (document.getElementById('broadcast-name') || { }).value || 'Sala criada sem nome',
                        isAudio: shared === 'audio'
                    });
                });
                hideUnnecessaryStuff();
                //gravar notify
                var  idCliente = $('#idCliente').val();
                var  idPrestador = $('#idPrestador').val();
                var idPedido = $('#idPedido').val();
                var Canal = $('#canal').val();

                $.ajax({
                     type: 'POST',
                     url: "NotifySalvar",
                     data: {
                     operacao: 'Salvar',
                     idCliente:idCliente,
                     idPedido:idPedido,
                     idPrestador: idPrestador,
                     Canal: Canal},
                 success: function (json)
                 {
                     if (json !== null && json === "")
                     {   
                        swal("Notificação enviada","O cliente foi avisado que você esta no vídeo chat.");
                     }
                     
                 },
                 beforeSend: function (xhr) {

                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     swal("Desculpe", "Algum erro inesperado ocorreu. " + errorThrown, "warning");
                 },
                 complete: function (jqXHR, textStatus) {        

                 }
                });
            }

            function captureUserMedia(callback) {
                var constraints = null;
                window.option = broadcastingOption ? broadcastingOption.value : '';
                if (option === 'Only Audio') {
                    constraints = {
                        audio: true,
                        video: false
                    };

                    if(DetectRTC.hasMicrophone !== true) {
                        alert('DetectRTC library is unable to find microphone; maybe you denied microphone access once and it is still denied or maybe microphone device is not attached to your system or another app is using same microphone.');
                    }
                }
                if (option === 'Screen') {
                    var video_constraints = {
                        mandatory: {
                            chromeMediaSource: 'screen'
                        },
                        optional: []
                    };
                    constraints = {
                        audio: false,
                        video: video_constraints
                    };

                    if(DetectRTC.isScreenCapturingSupported !== true) {
                       alert('DetectRTC library is unable to find screen capturing support. You MUST run chrome with command line flag "chrome --enable-usermedia-screen-capturing"');
                    }
                }

                if (option !== 'Only Audio' && option !== 'Screen' && DetectRTC.hasWebcam !== true) {
                    alert('DetectRTC library is unable to find webcam; maybe you denied webcam access once and it is still denied or maybe webcam device is not attached to your system or another app is using same webcam.');
                }

                var htmlElement = document.createElement(option === 'Only Audio' ? 'audio' : 'video');
                htmlElement.setAttribute('autoplay', true);
                htmlElement.setAttribute('controls', true);
                videosContainer.insertBefore(htmlElement, videosContainer.firstChild);


                var mediaConfig = {
                    video: htmlElement,
                    onsuccess: function(stream) {
                        config.attachStream = stream;
                        callback && callback();

                        htmlElement.setAttribute('muted', true);
                        rotateInCircle(htmlElement);
                    },
                    onerror: function() {
                        if (option === 'Only Audio') alert('unable to get access to your microphone');
                        else if (option === 'Screen') {
                            if (location.protocol === 'http:') alert('Please test this WebRTC experiment on HTTPS.');
                            else alert('Screen capturing is either denied or not supported. Are you enabled flag: "Enable screen capture support in getUserMedia"?');
                        } else alert('unable to get access to your webcam');
                    }
                };
                if (constraints) mediaConfig.constraints = constraints;
                getUserMedia(mediaConfig);
            }

            var broadcastUI = broadcast(config);

            /* UI specific */
            var videosContainer = document.getElementById('videos-container') || document.body;
            var setupNewBroadcast = document.getElementById('setup-new-broadcast');
            var roomsList = document.getElementById('rooms-list');

            var broadcastingOption = document.getElementById('broadcasting-option');

            if (setupNewBroadcast) setupNewBroadcast.onclick = setupNewBroadcastButtonClickHandler;

            function hideUnnecessaryStuff() {
                var visibleElements = document.getElementsByClassName('visible'),
                    length = visibleElements.length;
                for (var i = 0; i < length; i++) {
                    visibleElements[i].style.display = 'none';
                }
            }

            function rotateInCircle(video) {
                video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(0deg)';
                setTimeout(function() {
                    video.style[navigator.mozGetUserMedia ? 'transform' : '-webkit-transform'] = 'rotate(360deg)';
                }, 1000);
            }
           
        </script>
       <script src="../../bootstrap/js/popover.js" type="text/javascript"></script>
        <!-- commits.js is useless for you! It is not part of this WebRTC Experiment. -->
        <script src="//cdn.webrtc-experiment.com/commits.js" async> </script>
        <!--Bootstrap -->
        <script src="../../js/libs/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../../bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../../js/notifyFunctions.js" type="text/javascript"></script>
        <script src="../../js/libs/sweet-notify/sweetalert.min.js" type="text/javascript"></script>
    </body>
</html>
