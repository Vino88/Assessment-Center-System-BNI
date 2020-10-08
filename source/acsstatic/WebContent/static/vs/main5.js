/*
*  Copyright (c) 2015 The WebRTC project authors. All Rights Reserved.
*
*  Use of this source code is governed by a BSD-style license
*  that can be found in the LICENSE file in the root of the source
*  tree.
*/

'use strict';

/* globals MediaRecorder */

// This code is adapted from
// https://rawgit.com/Miguelao/demos/master/mediarecorder.html

'use strict';

/* globals MediaRecorder */

var mediaSource = new MediaSource();
mediaSource.addEventListener('sourceopen', handleSourceOpen, false);
var mediaRecorder;
var recordedBlobs;
var sourceBuffer;

var gumVideo = document.querySelector('video#gum');
var recordedVideo = document.querySelector('video#recorded');

var recordButton = document.querySelector('a#record');
var playButton = document.querySelector('a#play');
var downloadButton = document.querySelector('a#save');
recordButton.onclick = toggleRecording;
playButton.onclick = play;
downloadButton.onclick = download;

// window.isSecureContext could be used for Chrome
var isSecureOrigin = location.protocol === 'https:' ||
location.hostname === 'localhost';
if (!isSecureOrigin) {
  alert('getUserMedia() must be run from a secure origin: HTTPS or localhost.' +
    '\n\nChanging protocol to HTTPS');
  location.protocol = 'HTTPS';
}

// Use old-style gUM to avoid requirement to enable the
// Enable experimental Web Platform features flag in Chrome 49

var constraints = {
  audio: true,
  video: true
};
 function getBrowser() { 
    if(navigator.userAgent.indexOf("Chrome") != -1 )
    {
        return 'Chrome';
    }
    else if(navigator.userAgent.indexOf("Firefox") != -1 ) 
    {
         return 'Firefox';
    }
    else 
    {
       return 'unknown';
    }
    }

if(getBrowser() == "Chrome"){
	 constraints = {"audio": true, "video": { "mandatory": { "minWidth": 160, "maxWidth": 160, "minHeight": 120,"maxHeight": 120}, "optional": [] } };
}else if(getBrowser() == "Firefox"){
	 constraints = {audio: true,video: { width: { min: 160, ideal: 160, max: 240 }, height: { min: 120, ideal: 120, max: 180 }}}; 
} 
else
{
	constraints = {audio :true, video:true};
}

function handleSuccess(stream) {
  console.log('getUserMedia() got stream: ', stream);
  window.stream = stream;
  if (window.URL) {
    gumVideo.src = window.URL.createObjectURL(stream);
  } else {
    gumVideo.src = stream;
  }
}

function handleError(error) {
  console.log('navigator.getUserMedia error: ', error);
}

navigator.mediaDevices.getUserMedia(constraints).
    then(handleSuccess).catch(handleError);

function handleSourceOpen(event) {
  console.log('MediaSource opened');
  sourceBuffer = mediaSource.addSourceBuffer('video/webm; codecs="vp8"');
  console.log('Source buffer: ', sourceBuffer);
}

recordedVideo.addEventListener('error', function(ev) {
  console.error('MediaRecording.recordedMedia.error()');
  alert('Your browser can not play\n\n' + recordedVideo.src
    + '\n\n media clip. event: ' + JSON.stringify(ev));
}, true);

function handleDataAvailable(event) {
  if (event.data && event.data.size > 0) {
    recordedBlobs.push(event.data);
  }
}

function handleStop(event) {
  console.log('Recorder stopped: ', event);
}

var GLOBAL_NUM_OF_TRY_VS = 0;

function toggleRecording() {
  if (recordButton.textContent === 'Rekam') {
	  
		if(GLOBAL_NUM_OF_TRY_VS < 2)
			
		{
			 startRecording();
			}
		else
			{
			alertify.alert('Anda hanya bisa melakukan dua kali pengambilan gambar');
			}
   
  } else {
    stopRecording();
    recordButton.innerHTML = 'Rekam';
    playButton.style.visibility = "visible";
    downloadButton.style.visibility = "visible";
  }
}

// The nested try blocks will be simplified when Chrome 47 moves to Stable
function startRecording() {
  recordedBlobs = [];
  var options = {mimeType: 'video/webm;codecs=vp9'};
  if (!MediaRecorder.isTypeSupported(options.mimeType)) {
    console.log(options.mimeType + ' is not Supported');
    options = {mimeType: 'video/webm;codecs=vp8'};
    if (!MediaRecorder.isTypeSupported(options.mimeType)) {
      console.log(options.mimeType + ' is not Supported');
      options = {mimeType: 'video/webm'};
      if (!MediaRecorder.isTypeSupported(options.mimeType)) {
        console.log(options.mimeType + ' is not Supported');
        options = {mimeType: ''};
      }
    }
  }
  try {
    mediaRecorder = new MediaRecorder(window.stream, options);
  } catch (e) {
    console.error('Exception while creating MediaRecorder: ' + e);
    alert('Exception while creating MediaRecorder: '
      + e + '. mimeType: ' + options.mimeType);
    return;
  }
  console.log('Created MediaRecorder', mediaRecorder, 'with options', options);
  recordButton.innerHTML = 'Berhenti';
  playButton.style.visibility = "hidden";
  downloadButton.style.visibility = "hidden";
  mediaRecorder.onstop = handleStop;
  mediaRecorder.ondataavailable = handleDataAvailable;
  mediaRecorder.start(10); // collect 10ms of data
  console.log('MediaRecorder started', mediaRecorder);
  
  onStartRecordingAction();
}

function stopRecording() {
  mediaRecorder.stop();
  console.log('Recorded Blobs: ', recordedBlobs);
  recordedVideo.controls = true;
  onStopRecordingAction();
}

function play() {
  var superBuffer = new Blob(recordedBlobs, {type: 'video/webm'});
  recordedVideo.src = window.URL.createObjectURL(superBuffer);
}
