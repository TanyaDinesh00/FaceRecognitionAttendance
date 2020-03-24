import csv
from tkinter import *
import cv2
import numpy as np
import os
from PIL import Image


def TrainImages():
    recognizer = cv2.face_LBPHFaceRecognizer.create()
    harcascadePath = "haarcascade_frontalface_default.xml"
    detector = cv2.CascadeClassifier(harcascadePath)
    faces, Id = getImagesAndLabels("TrainingImage")
    recognizer.train(faces, np.array(Id))
    recognizer.save(".\TrainingImageLabel\Trainner.yml")
    print("Images Trained for:")
    with open('StudentDetails\StudentDetails.csv') as csvFile:

        csv_reader = csv.reader(csvFile, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if not len(row):
                continue
            else:
                print(f'\t{row[0]}  {row[1]}')
    csvFile.close()
    # message.configure(text=res)


def getImagesAndLabels(path):
    imagePaths = [os.path.join(path, f) for f in os.listdir(path)]
    faces = []
    Ids = []
    for imagePath in imagePaths:
        pilImage = Image.open(imagePath).convert('L')
        imageNp = np.array(pilImage, 'uint8')
        Id = int(os.path.split(imagePath)[-1].split(".")[1])
        faces.append(imageNp)
        Ids.append(Id)
    return faces, Ids


TrainImages()
