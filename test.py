# OpenCV のインポート
import cv2
import matplotlib.pyplot as plt

cap = cv2.VideoCapture(0) #カメラ映像取得

start = cv2.getTickCount() # 時間カウント開始

x=[]
y=[]

while True:
    _ , frame = cap.read() # VideoCaptureから1フレーム読み込む

    cv2.imshow('Raw Frame', frame) # 加工無しの映像表示

    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY) # グレースケールの映像をgrayに入力

    mean , _ , _ , _ = cv2.mean(gray) # 輝度値の平均を計算
    #print(mean)
    y.append(mean)

    end = cv2.getTickCount() # 処理時間計測終了
    time = (end - start)/cv2.getTickFrequency() # 処理時間算出
    #print(time)
    x.append(time)

    plt.plot(x,y)
    plt.draw()
    plt.pause(0.01)

    k = cv2.waitKey(1) # キー入力を1ms待って、k が27（ESC）だったらBreakする
    if k == 27:
        break

# キャプチャをリリースして、ウィンドウをすべて閉じる
cap.release()
cv2.destroyAllWindows()
