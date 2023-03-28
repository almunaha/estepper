# -*- coding: utf-8 -*-
from com.estepper.estepper.service import PythonService
# import dataset
# from sklearn.feature_extraction.text import CountVectorizer
# from sklearn.cluster import KMeans


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
        #     alimentos = [row['nombre'] for row in db['alimentacion'].all()]

        #     recetas = []
        #     for row in db['recetas'].all():
        #         receta = (row['nombre'], row['ingredientes'].split(','))
        #         recetas.append(receta)

        #     # Cerrar la conexión
        #     db.close()
        # # DESCARTAR ALIMENTOS QUE ESTÉN EN DONTWANT
        # # HACER CLUSTER CON ALIMENTOS
        # # Descartar alimentos que están en dontwant
        #     alimentos = [alimento for alimento in alimentos if alimento not in dontwant]

        # # Hacer cluster con los alimentos
        #     vectorizer = CountVectorizer(tokenizer=lambda x: x.split(','))
        #     X = vectorizer.fit_transform(alimentos)
        #     kmeans = KMeans(n_clusters=5).fit(X)

        #     # Obtener las categorías
        #     categorias = {}
        #     for i, categoria in enumerate(kmeans.labels_):
        #         if categoria not in categorias:
        #             categorias[categoria] = []
        #         categorias[categoria].append(alimentos[i])
        #     # COGER ALIMENTOS QUE ESTÉN EN EL MISMO CLASTER QUE LOS WANTS
        #     # COGER RECETAS CON ALGUNO DE ESOS ALIMENTOS
        #     # Obtener alimentos que están en la misma categoría que los want
        #     alimentos_interesantes = []
        #     for categoria, alimentos_categoria in categorias.items():
        #         for alimento in alimentos_categoria:
        #             if alimento in want:
        #                 alimentos_interesantes.extend(alimentos_categoria)
        #                 break

        #     # Obtener recetas que contienen los alimentos interesantes
        #     recetas_interesantes = set()
        #     for receta_nombre, receta_ingredientes in recetas:
        #         for ingrediente in receta_ingredientes:
        #             if ingrediente in alimentos_interesantes:
        #                 recetas_interesantes.add(receta_nombre)

        #     return list(recetas_interesantes)
        return ["1", "2", "3"]