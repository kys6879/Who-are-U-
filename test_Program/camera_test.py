from picamera import PiCamera
import time
camera = PiCamera()

camera.start_preview(fullscreen=False,window=(100,20,640,480))
time.sleep(1)
camera.stop_preview()
