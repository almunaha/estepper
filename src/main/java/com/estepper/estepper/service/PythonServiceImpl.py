from com.estepper.estepper.service import PythonService

class PythonServiceImpl(PythonService):
    def __init__(self):
        self.value="recomendaciones"
       
    def getHello(self):
        return self.value
    
    def recetasparecidas(self, want, dontwant):
        # CONECTAR BASE DE DATOS Y EXTRAER LISTA DE ALIMENTOS Y RECETAS
            # from com.ziclick.python.sql import ZXJDBC
            # db = ZXJDBC.connect()
        # DESCARTAR ALIMENTOS QUE ESTÉN EN DONTWANT
        # HACER CLUSTER CON ALIMENTOS
        # COGER ALIMENTOS QUE ESTÉN EN EL MISMO CLASTER QUE LOS WANTS
        # COGER RECETAS CON ALGUNO DE ESOS ALIMENTOS
        return ["1", "2", "3"]