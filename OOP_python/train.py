import csv
import tkinter as tk
from tkinter import *
from tkinter import messagebox
import cv2

window = tk.Tk()
window.title("OOP Project")

window.geometry('800x500')
C = Canvas(window, bg="blue", height=250, width=300)
filename = PhotoImage(file=".\\Jagged2.png")
background_label = Label(window, image=filename)
background_label.place(x=0, y=0, relwidth=1, relheight=1)

C.pack()

# window.attributes('-fullscreen', True)

window.grid_rowconfigure(0, weight=1)
window.grid_columnconfigure(0, weight=1)

message = tk.Label(window, text="Register Students:", bg="#d45a00", fg="black", width=14,
                   height=1, font=('Monospaced', 19, ''))
message.place(x=310, y=60)

lbl = tk.Label(window, text="Enter ID", width=8, height=1, fg="black", bg="#d45a00", font=('Monospaced', 15, '  '))
lbl.place(x=180, y=150)
txt = tk.Entry(window, width=20, bg="#d45a00", fg="black", font=('Monospaced', 15, '  '))
txt.place(x=400, y=150)
lbl2 = tk.Label(window, text="Enter Name", width=10, height=1, fg="black", bg="#d45a00", font=('Monospaced', 15, '  '))
lbl2.place(x=180, y=250)
txt2 = tk.Entry(window, width=20, bg="#d45a00", fg="black", font=('Monospaced', 15, '  '))
txt2.place(x=400, y=250)
lbl3 = tk.Label(window, text="Notification : ", width=10, fg="black", bg="#d45a00", height=1,
                font=('Monospaced', 15, '  '))
lbl3.place(x=180, y=350)
message = tk.Label(window, text="", bg="#d45a00", fg="black", width=23, height=2, activebackground="blue",
                   font=('Monospaced', 15, '  '))
message.place(x=400, y=340)


def is_number(s):
    try:
        float(s)
        return True
    except ValueError:
        pass

    try:
        import unicodedata
        unicodedata.numeric(s)
        return True
    except (TypeError, ValueError):
        pass

    return False


def TakeImages():
    Id = (txt.get())
    name = (txt2.get())
    if is_number(Id) and name.isalpha():
        cam = cv2.VideoCapture(0)
        harcascadePath = "haarcascade_frontalface_default.xml"
        detector = cv2.CascadeClassifier(harcascadePath)
        sampleNum = 0
        while (True):
            ret, img = cam.read()
            gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
            faces = detector.detectMultiScale(gray, 1.3, 5)
            for (x, y, w, h) in faces:
                cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)
                sampleNum = sampleNum + 1
                cv2.imwrite("TrainingImage\ " + name + "." + Id + '.' + str(sampleNum) + ".jpg", gray[y:y + h, x:x + w])
                cv2.imshow('frame', img)
            if cv2.waitKey(100) & 0xFF == ord('q'):
                break
            elif sampleNum > 100:
                break
        cam.release()
        cv2.destroyAllWindows()
        res = "Images Saved for ID : " + Id + " Name : " + name
        row = [Id, name]
        with open('StudentDetails\StudentDetails.csv', 'a+') as csvFile:
            writer = csv.writer(csvFile)
            writer.writerow(row)
        csvFile.close()
        message.configure(text=res)
    else:
        if (is_number(Id)):
            res = "Enter Alphabetical Name"
            message.configure(text=res)
        if (name.isalpha()):
            res = "Enter Numeric Id"
            message.configure(text=res)


def quit():
    dialog_title = 'QUIT'
    dialog_text = 'Are you sure?'
    answer = messagebox.askyesno(dialog_title, dialog_text)
    if answer == TRUE:
        window.destroy()


takeImg = tk.Button(window, text="Take Images", command=TakeImages, fg="black", bg="#b0e0e6", width=10, height=1,
                    activebackground="blue", font=('Monospaced', 13, '  '))
takeImg.place(x=200, y=420)

quitWindow = tk.Button(window, text="Quit", command=quit, fg="black", bg="#b0e0e6", width=10, height=1,
                       activebackground="blue", font=('Monospaced', 13, '  '))
quitWindow.place(x=450, y=420)

window.mainloop()
