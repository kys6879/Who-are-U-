import tkinter

# object instance creation
# 객체 인스턴스 생성
root = tkinter.Tk()

# label 생성
label = tkinter.Label(root, text="hello!!")
label2 = tkinter.Label(root, text="hello!!")
# label 배치
label.pack()
label2.pack()
# root 표시
root.mainloop()
