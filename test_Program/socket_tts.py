import socket
import os
import sys
import urllib.request
import pygame
import time


pygame.init()
client_id = "_NcqU0x4Dar0uSgUxEwZ"
client_secret = "7ARCUWsj7G"


HOST = ""
PORT = 8888
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print ('Socket created')
s.bind((HOST, PORT))
print ('Socket bind complete')
s.listen(1)
print ('Socket now listening')

#파이 컨트롤 함수
def do_some_stuffs_with_input(input_string):
	#라즈베리파이를 컨트롤할 명령어 설정
        
	if input_string == "left":
		input_string = "ddd"
		#파이 동작 명령 추가할것
	elif input_string == "right":
		input_string = "서보모터 우회전 합니다."
	elif input_string == "single":
		input_string = "사진을 찍습니다."
	else :
		input_string = input_string

	return input_string

def make_tts(client_id,client_secret,msg):
    encText = urllib.parse.quote(msg)
    data = "speaker=mijin&speed=0&text=" + encText;
    url = "https://openapi.naver.com/v1/voice/tts.bin"
    request = urllib.request.Request(url)
    request.add_header("X-Naver-Client-Id",client_id)
    request.add_header("X-Naver-Client-Secret",client_secret)
    response = urllib.request.urlopen(request, data=data.encode('utf-8'))
    rescode = response.getcode()
    if(rescode==200):
        print("TTS mp3 저장")
        print("전달된 메세지 : {}".format(msg))
        response_body = response.read()
        with open('1111.mp3', 'wb') as f:
            f.write(response_body)
            pygame.mixer.music.load("1111.mp3")
            pygame.mixer.music.play()
            time.sleep(5)
            pygame.mixer.music.stop()
    else:
        print("Error Code:" + rescode)

while True:
	#접속 승인
	conn, addr = s.accept()
	#print("Connected by ", addr)

	#데이터 수신
	data = conn.recv(1024)
	data = data.decode("utf8").strip()
	if not data: break
	print("Received: " + data)

	#수신한 데이터로 파이를 컨트롤 
	res = do_some_stuffs_with_input(data)
	#print("파이 동작 :" + res)

	make_tts(client_id,client_secret,data)

	#클라이언트에게 답을 보냄
	#conn.sendall(res.encode("utf-8"))
	#연결 닫기
	conn.close()
s.close()
