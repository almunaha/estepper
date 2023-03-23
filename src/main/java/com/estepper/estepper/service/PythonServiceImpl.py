from com.estepper.estepper.service import PythonService

class PythonServiceImpl(PythonService):
    def __init__(self):
        self.value="index"
       
    def getHello(self):
        return self.value