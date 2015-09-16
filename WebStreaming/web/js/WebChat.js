/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var pc = new RTCPeerConnection(null);
pc.onaddstream = gotRemoteStream;
pc.addStream(localStream);
pc.createOffer(gotOffer);

function gotOffer(desc) {
pc.setLocalDescription(desc);
sendOffer(desc);
}

function gotAnswer(desc) {
pc.setRemoteDescription(desc);
}

function gotRemoteStream(e) {
attachedMediaStream(remoteVideo, e.stream);
}