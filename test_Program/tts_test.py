#-*- coding: utf-8 -*-

import os
import sys
import urllib.request
#import pygame
#import time
pygame.init()
client_id = "_NcqU0x4Dar0uSgUxEwZ"
client_secret = "7ARCUWsj7G"
encText = urllib.parse.quote("sex sex sex")
data = "speaker=mijin&speed=5&text=" + encText;
url = "https://openapi.naver.com/v1/voice/tts.bin"
request = urllib.request.Request(url)
request.add_header("X-Naver-Client-Id",client_id)
request.add_header("X-Naver-Client-Secret",client_secret)
response = urllib.request.urlopen(request, data=data.encode('utf-8'))
rescode = response.getcode()
if(rescode==200):
    print("TTS mp3 저장")
    response_body = response.read()
    with open('1111.mp3', 'wb') as f:
        f.write(response_body)
        #pygame.mixer.music.load("1111.mp3")
        #pygame.mixer.music.play()
        #time.sleep(1)
        #pygame.mixer.music.stop()
else:
    print("Error Code:" + rescode)
