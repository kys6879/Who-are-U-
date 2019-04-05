import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)
GPIO.setup(18,GPIO.IN)

button = GPIO.input(18)

try:
            while(True):
                        if button == False:
                                    print("hello")

except KeyboardInterrupt:
            GPIO.cleanup()


