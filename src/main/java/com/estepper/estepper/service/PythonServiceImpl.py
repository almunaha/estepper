# -*- coding: utf-8 -*-
from com.estepper.estepper.service import PythonService
import dataset
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.cluster import KMeans
from sklearn.metrics.pairwise import cosine_similarity
import numpy as np


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
        # # HACER CLUSTER CON ALIMENTOS HACIENDO LA CLASIFICACIÓN EN FUNCIÓN DE LA SIMILITUD DE COSENO Y DISTANCIA PARA VER CÓMO DE PARECIDOS SON LOS NUTRIENTES Y CREAR ALIMENTOS HIPERPROTEICOS, INFRAPROTEICOS, ETCÉTERA
    
        #     # Descartar alimentos que están en dontwant
        #     alimentos = [alimento for alimento in alimentos if alimento not in dontwant]

        #     # Hacer cluster con los alimentos
        #     vectorizer = CountVectorizer(tokenizer=lambda x: x.split(','))
        #     X = vectorizer.fit_transform([','.join(a[-1]) for a in alimentos])
            
        #     # Clasificar los alimentos en hiperproteicos o infraproteicos en función de la cantidad de proteínas
        #     alimentos_cluster = []
        #     for i, alimento in enumerate(alimentos):
        #         if float(alimento[2]) > 20: #alimento[2] xq es la columna correspondiente a proteinas
        #             alimentos_cluster.append("hiperproteico")
        #         else:
        #             alimentos_cluster.append("infraproteico")

        #     # Unir los alimentos con su categoría correspondiente
        #     alimentos_categorias = list(zip(alimentos, alimentos_cluster))

        #     # Clasificar los alimentos en función de su similitud de coseno y distancia euclidiana
        #     kmeans = KMeans(n_clusters=5).fit(X)
        #     similarities = cosine_similarity(X)
        #     distances = np.sqrt(((X - X[:, np.newaxis])**2).sum(axis=2))
            
        #     # COGER ALIMENTOS QUE ESTÉN EN EL MISMO CLUSTER QUE LOS WANTS
        #     # COGER RECETAS CON ALGUNO DE ESOS ALIMENTOS
        #     # Coger los índices de los alimentos en el cluster que contienen los wants
        #     want_indices = []
        #     for w in want:
        #         for i, c in enumerate(kmeans.labels_):
        #             if w in alimentos[i][-1] and i not in want_indices:
        #                 want_indices.append(i)

        #     # Obtener los alimentos del mismo cluster que los wants
        #     alimentos_cluster_wants = [alimentos_categorias[i] for i in want_indices]

        #     # Obtener las recetas que contienen los alimentos del cluster
        #     recetas_con_alimentos_cluster = []
        #     for i, receta in enumerate(recetas):
        #         ingredientes_receta = [ingrediente[1] for ingrediente in ingredientes if ingrediente[0] == receta[1][0]]
        #         for a in alimentos_cluster_wants:
        #             if a[0][-1][0] in ingredientes_receta:
        #                 recetas_con_alimentos_cluster.append(receta[0])
        #                 break

        #     return recetas_con_alimentos_cluster

            
        return ["1", "2", "3"]