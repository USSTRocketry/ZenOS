import sys
import random
from PyQt5 import QtWidgets
import pyqtgraph as pg
import numpy as np

class MainWindow(QtWidgets.QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Live Graphs Example")
        self.setGeometry(100, 100, 800, 600)

        self.graphWidget = pg.GraphicsLayoutWidget()
        self.setCentralWidget(self.graphWidget)

        self.plot1 = self.graphWidget.addPlot(title="Random Data 1")
        self.plot2 = self.graphWidget.addPlot(title="Random Data 2", row=1, col=0)

        self.data1 = np.random.normal(size=100)
        self.data2 = np.random.normal(size=100)

        self.curve1 = self.plot1.plot(self.data1, pen='r')
        self.curve2 = self.plot2.plot(self.data2, pen='b')

        self.timer = pg.QtCore.QTimer()
        self.timer.timeout.connect(self.update)
        self.timer.start(1000)

    def update(self):
        self.data1 = np.roll(self.data1, -1)
        self.data1[-1] = random.random()
        self.curve1.setData(self.data1)

        self.data2 = np.roll(self.data2, -1)
        self.data2[-1] = random.random()
        self.curve2.setData(self.data2)

if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    mainWin = MainWindow()
    mainWin.show()
    sys.exit(app.exec_())
