# -*- coding: utf-8 -*-
from com.estepper.estepper.service import PythonService
# import dataset
# from sklearn.feature_extraction.text import CountVectorizer
# from sklearn.cluster import KMeans
# from sklearn.metrics.pairwise import cosine_similarity
# import numpy as np


class PythonServiceImpl(PythonService):
    def __init__(self):
        self.value = "recomendaciones"

    def getHello(self):
        return self.value

    def recetasparecidas(self, want, dontwant):
        # # CONECTAR BASE DE DATOS Y EXTRAER LISTA DE ALIMENTOS Y RECETAS
        #     # Conectar a la base de datos
        #     db = dataset.connect(
        #         'mysql://estepper:estepper@localhost:3306/estepper')

        #     # Obtener los alimentos y recetas
        #     alimentos = []
        #     for row in db['alimentacion'].all():
        #         alimento = (row['nombre'], row['sal'], row['proteinas'], row['hidratos_de_carbono'], row['fibra_alimentaria'], row['grasas_saturadas'], row['id'].split(','))
        #         alimentos.append(alimento)
        #     for alimento in alimentos:
        #         db[alimento] = db[alimento].fillna('')

        #     recetas = []
        #     for row in db['recetas'].all():
        #         receta = (row['nombre'], row['id'].split(','))
        #         recetas.append(receta)
            
        #     ingredientes = []
        #     for row in db['receta_alimentacion'].all():
        #         ingrediente = (row['receta_id'], row['alimentacion_id'].split(','))
        #         ingredientes.append(ingrediente)

        #     # Cerrar la conexión
        #     db.close()
        # # DESCARTAR ALIMENTOS QUE ESTÉN EN DONTWANT
        # # HACER CLUSTER CON ALIMENTOS HACIENDO LA CLASIFICACIÓN EN FUNCIÓN DE LA SIMILITUD DE COSENO PARA VER CÓMO DE PARECIDOS SON
    
        #     # Descartar alimentos que están en dontwant
        #     alimentos = [alimento for alimento in alimentos if alimento not in dontwant]

        #     # Hacer cluster con los alimentos
        #     cv = CountVectorizer()
        #     count_matrix = cv.fit_transform(alimentos)
        #     cosine_sim = cosine_similarity(count_matrix)

        #     similar_alimentos = list(enumerate(cosine_sim))
            
        #     # COGER Similar_ALIMENTOS QUE ESTÉN EN EL MISMO CLUSTER QUE LOS WANTS
        #     # COGER RECETAS CON ALGUNO DE ESOS ALIMENTOS

        #     alimentos_cluster = []
        #     for i, _ in similar_alimentos:
        #         if i in want:
        #             for a in alimentos:
        #                 if alimentos.index(a) == i:
        #                     alimentos_cluster.append(a)
        #             for j in range(0, len(cosine_sim[i])):
        #                 if cosine_sim[i][j] > 0.5 and j not in want:
        #                     for a in alimentos:
        #                         if alimentos.index(a) == j:
        #                             alimentos_cluster.append(a)

        #     # Obtener las recetas que contienen los alimentos del cluster
        #     recetas_ids = []
        #     for r in recetas:
        #         for a in alimentos_cluster:
        #             if str(a[6]) in r[1]:
        #                 recetas_ids.append(r[1])

        #     # Obtener los ids de las recetas y devolverlos
        #     recetas_ids = [r for receta_id in recetas_ids for r in receta_id]
        #     return recetas_ids
            
        return ["1", "2", "3"]