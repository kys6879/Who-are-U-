import RPi.GPIO as GPIO
import time
import serial
from picamera import PiCamera
from datetime import datetime
import socket
import pygame
import urllib.request
import os
import sys

def play_sound():
    ser.write("P\r\n".encode())

def camera_preview(preview_time):
    print("{}초 만큼 동작됩니다.".format(preview_time))
    time.sleep(1)
    camera.start_preview(fullscreen=False,window=(100,20,640,480))
    time.sleep(preview_time)
    camera.stop_preview()
    
def shoot(now_date):
    print("[사진촬영]을 하시겠습니까?")
    print("YES : 1")
    print("NO : 2")

    what = int(input())
    if what==1:
        camera.capture('/home/pi/Desktop/{}.jpg'.format(now_date))
        print("Successful.")
    else:
        pass
    
    
def init():
    print("------------------------")
    time.sleep(0.3)
    print("| Hello!!              |")
    time.sleep(0.3)
    print("| IoT Project          |")
    time.sleep(0.3)
    print("| 당신은 누구십니까?     |")
    time.sleep(0.3)
    print("| DEV 0.8              |")
    time.sleep(0.3)
    print("------------------------\n\n\n\n")
    time.sleep(0.3)
    print("대기상태입니다.")

#파이 컨트롤 함수
def do_some_stuffs_with_input(input_string):
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
        #print("TTS mp3 저장")
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
def call_camera():
    #play_sound()
    print("[방문자]가 [있습니다].")
    print("[방문자]를 [확인]하시겠습니까?")
    print("네 : 1 ")
    print("아니요 :2")

    check = int(input())

    if check==1:
        print("[몇초]동안 [확인]하시겠습니까?")
        print("1~10 까지의 숫자를 입력해주세요 . (단위 : 초)")
        preview_time = int(input())
        camera_preview(preview_time)

    print("[음성메세지]를 [전송]하시겠습니까?")
    print("네 : 1 ")
    print("아니요 :2")
    check = int(input())
    
    if check==1:
        print("[어플리케이션]을 실행후 [텍스트]를 입력해주세요.")
        conn, addr = s.accept()
        data = conn.recv(1024)
        data = data.decode("utf8").strip()
        res = do_some_stuffs_with_input(data)
        make_tts(client_id,client_secret,data)
        conn.close()

def test_shoot_socket(input_string):
            return input_string

#### GPIO SETTING #### 
#GPIO.setmode(GPIO.BCM)
#GPIO.setup(18,GPIO.IN)
#### Serial SETTING ####
#arduino_port = "/dev/ttyACM0"
#ser = serial.Serial(arduino_port,9600)
#### Network SETTING ####
pygame.init()
#client_id = "_NcqU0x4Dar0uSgUxEwZ"
#client_secret = "7ARCUWsj7G"
HOST = ""
PORT = 5555
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print ('소켓생성완료')
s.bind((HOST, PORT))
print ('바인드중..')
s.listen(1)
print ('소켓준비완료')
print('네트워크 셋팅이 완료되었습니다.')

camera = PiCamera()
now = datetime.now()
a=1
now_year = str(now.year)
now_month = str(now.month)
now_day = str(now.day)
now_hour = str(now.hour)
now_minute = str(now.minute)
now_date = now_year + now_month + now_day + now_hour + now_minute



"""camera = PiCamera()
now = datetime.now()
a=1
now_year = str(now.year)
now_month = str(now.month)
now_day = str(now.day)
now_hour = str(now.hour)
now_minute = str(now.minute)
now_date = now_year + now_month + now_day + now_hour + now_minute """

#init()
try:
    while(True):
                conn,addr = s.accept()
                print("by",addr)

                #recive
                data = conn.recv(1024)
                data = data.decode("utf8").strip()
                if not data: break
                print("rc:"+data)

                res = int(test_shoot_socket(data))
                if res==1:
                            camera.capture('/home/pi/Desktop/{}.jpg'.format(now_date))
                if res==2:
                            print("movie")

                print("hello")
                conn.close()
                                                

except KeyboardInterrupt:
            GPIO.cleanup()
