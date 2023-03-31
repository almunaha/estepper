# -*- coding: utf-8 -*-
from com.estepper.estepper.service import PythonService
# import jaydebeapi
# from sklearn.feature_extraction.text import CountVectorizer
# from sklearn.metrics.pairwise import cosine_similarity

class PythonServiceImpl(PythonService):
    def __init__(self):
        self.value = "recomendaciones"

    def getHello(self):
        return self.value

    def recetasparecidas(self, want, dontwant):
        # # CONECTAR BASE DE DATOS Y EXTRAER LISTA DE ALIMENTOS Y RECETAS

        # # Conectar a la base de datos
        # conn = jaydebeapi.connect(
        #     "com.mysql.jdbc.Driver",
        #     "jdbc:mysql://localhost:3306/estepper",
        #     ["estepper", "estepper"],
        # )

        # # Obtener los alimentos y recetas
        # alimentos = []
        # with conn.cursor() as cur:
        #     cur.execute(
        #         "SELECT nombre, sal, proteinas, hidratos_de_carbono, fibra_alimentaria, grasas_saturadas, id FROM alimentacion"
        #     )
        #     for row in cur:
        #         alimento = (
        #             row[0],
        #             row[1],
        #             row[2],
        #             row[3],
        #             row[4],
        #             row[5],
        #             row[6].split(","),
        #         )
        #         alimentos.append(alimento)

        # recetas = []
        # with conn.cursor() as cur:
        #     cur.execute("SELECT nombre, id FROM recetas")
        #     for row in cur:
        #         receta = (row[0], row[1].split(","))
        #         recetas.append(receta)

        # ingredientes = []
        # with conn.cursor() as cur:
        #     cur.execute("SELECT receta_id, alimentacion_id FROM receta_alimentacion")
        #     for row in cur:
        #         ingrediente = (row[0], row[1].split(","))
        #         ingredientes.append(ingrediente)

        # # Cerrar la conexión
        # conn.close()

        # # DESCARTAR ALIMENTOS QUE ESTÉN EN DONTWANT
        # # HACER CLUSTER CON ALIMENTOS HACIENDO LA CLASIFICACIÓN EN FUNCIÓN DE LA SIMILITUD DE COSENO PARA VER CÓMO DE PARECIDOS SON

        # # Descartar alimentos que están en dontwant
        # alimentos = [alimento for alimento in alimentos if str(
        #     alimento[6]) not in dontwant]

        # # Hacer cluster con los alimentos
        # cv = CountVectorizer()
        # count_matrix = cv.fit_transform([alimento[0] for alimento in alimentos])
        # cosine_sim = cosine_similarity(count_matrix)

        # similar_alimentos = list(enumerate(cosine_sim))

        # # COGER Similar_ALIMENTOS QUE ESTÉN EN EL MISMO CLUSTER QUE LOS WANTS
        # # COGER RECETAS CON ALGUNO DE ESOS ALIMENTOS

        # alimentos_cluster = []
        # for i, _ in similar_alimentos:
        #     if str(i) in want:
        #         for a in alimentos:
        #             if str(a[6]) == str(i):
        #                 alimentos_cluster.append(a)
        #         for j in range(0, len(cosine_sim[i])):
        #             if cosine_sim[i][j] > 0.5 and str(j) not in want:
        #                 for a in alimentos:
        #                     if str(a[6]) == str(j):
        #                         alimentos_cluster.append(a)

        # # Obtener las recetas que contienen los alimentos del cluster
        # recetas_ids = []
        # for r in recetas:
        #     for a in alimentos_cluster:
        #         if str(a[6]) in r[1]:
        #             recetas_ids.append(r[1])

        # # Obtener los ids de las recetas y devolverlos
        # recetas_ids = [r for receta_id in recetas_ids for r in receta_id]
        # return recetas_ids

        return ["1", "2", "3"]
