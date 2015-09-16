/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


navigator.getMedia = ( navigator.getUserMedia ||
navigator.webkitGetUserMedia ||
navigator.mozGetUserMedia ||
navigator.msGetUserMedia);
navigator.getMedia (
// constraints
{
video: true,
audio: true
},
// successCallback
function(localMediaStream) {
var video = document.getElementsByTagName('video')[0];
video.src = window.URL.createObjectURL(localMediaStream);
video.onloadedmetadata = function(e) {
// Do something with the video here.
video.play();
};
},
// errorCallback
function(err) {
console.log("The following error occurred: " + err);
}
);