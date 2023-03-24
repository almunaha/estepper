from com.estepper.estepper.service import PythonService

class PythonServiceImpl(PythonService):
    def __init__(self):
        self.value="recomendaciones"
       
    def getHello(self):
        return self.value
    
    def recetasparecidas(self, want, dontwant):
        # from com.ziclick.python.sql import ZXJDBC
        # db = ZXJDBC.connect()
        return ["1", "2", "3"]