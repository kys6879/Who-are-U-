import pygame
import time

pygame.init()

pygame.mixer.music.load("11111.mp3")

pygame.mixer.music.play()
time.sleep(3)
pygame.mixer.music.stop()
