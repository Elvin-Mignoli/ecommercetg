<%-- 
    Document   : Channel1
    Created on : 14/09/2015, 22:14:31
    Author     : Elvin
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>WebRTC Broadcasting ® Muaz Khan</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
        <link rel="author" type="text/html" href="https://plus.google.com/+MuazKhan">
        <meta name="author" content="Muaz Khan">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link href="../../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="//cdn.webrtc-experiment.com/style.css">
        
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

           input {
                border: 1px solid #d9d9d9;
                border-radius: 1px;
                font-size: 150%;
                margin-top: 5px;
                margin-bottom: 8px;
                height: 55px;
               
                
            }

            .setup {
                border-bottom-left-radius: 0;
                border-top-left-radius: 0;
                font-size: 102%;
                height: 55px;
                margin-top: 5px;
                margin-bottom: 8px;
                position: absolute;
                font-size: 150%;;
            }
            select {
                border: 1px solid #d9d9d9;
                border-radius: 1px;
                height:55px;
                margin-top: 5px;
                margin-bottom: 8px;
                padding: 1.1em;
                vertical-align: 6px;
                font-size: 130%;  
            }   
            li{
                font-size: 120%;
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
                <li class="active"><a href="PrestadorDashboard.jsp">Home</a></li>
                <li class="dropdown">
                  <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Page 1-1</a></li>
                    <li><a href="#">Page 1-2</a></li>
                    <li><a href="#">Page 1-3</a></li>
                  </ul>
                </li>
                <li><a href="#">Page 2</a></li>
                <li><a href="#">Page 3</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="logoff"><span class="glyphicon glyphicon-log-in" id='logoff'></span> Sair</a></li>
              </ul>
            </div>
          </div>
        </nav><!--fim Navbar -->
        
        <!-- Body Web Broadcasting-->
        <div class="container-fluid col-lg-12">
            <!-- copy this <section> and next <script> -->
            <section class="experiment">                
                <section>
                    <select id="broadcasting-option" class="col-lg-2">
                        <option>Audio + Video</option>
                        <option>Only Audio</option>
                    </select>
                    <input  class="col-lg-7" type="text" placeholder="Digite seu nome" id="broadcast-name">
                    <button id="setup-new-broadcast" class="setup col-lg-2">Criar a sala de videoconferência</button>
                </section>

                <!-- list of all available broadcasting rooms -->
                <table class="table-responsive" style="width: 100%; font-size: 130%;" id="rooms-list"></table>

                <!-- local/remote videos container -->
                <div id="videos-container"></div>

            </section>
            
        </div>
        
        <script>
            // Muaz Khan     - https://github.com/muaz-khan
            // MIT License   - https://www.webrtc-experiment.com/licence/
            // Documentation - https://github.com/muaz-khan/WebRTC-Experiment/tree/master/webrtc-broadcasting

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
                        roomName: (document.getElementById('broadcast-name') || { }).value || 'Anonymous',
                        isAudio: shared === 'audio'
                    });
                });
                hideUnnecessaryStuff();
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

                if (option != 'Only Audio' && option != 'Screen' && DetectRTC.hasWebcam !== true) {
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
       
        <!-- commits.js is useless for you! It is not part of this WebRTC Experiment. -->
        <script src="//cdn.webrtc-experiment.com/commits.js" async> </script>
        <!--Bootstrap -->
        <script src="../../js/libs/jquery-1.11.1.min.js" type="text/javascript"></script>
        <script src="../../bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
