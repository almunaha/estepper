from com.estepper.estepper.service import PythonService
# -*- coding: utf-8 -*-
import jaydebeapi
import math

class PythonServiceImpl(PythonService):
    def __init__(self):
        self.value = "recomendaciones"

    def getHello(self):
        return self.value
        
    def recetasparecidas(self, want, dontwant):
        # FUNCIONES DE MACHINE LEARNING:
        def cosine_similarity(vector1, vector2):
            dot_product = sum(p*q for p,q in zip(vector1, vector2))
            magnitude1 = math.sqrt(sum([val**2 for val in vector1]))
            magnitude2 = math.sqrt(sum([val**2 for val in vector2]))
            if magnitude1 and magnitude2:
                return dot_product / (magnitude1 * magnitude2)
            else:
                return 0.0
    
        def count_words(text):
            words = text.lower().split()
            return {word: words.count(word) for word in words}
        # CONECTAR BASE DE DATOS Y EXTRAER LISTA DE ALIMENTOS Y RECETAS

        # Conectar a la base de datos
        conn = jaydebeapi.connect(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/estepper",
            ["estepper", "estepper"],
        )

        # Obtener los alimentos y recetas
        alimentos = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT sal, proteinas, hidratos_de_carbono, fibra_alimentaria, grasas_saturadas, id FROM alimentacion"
            )
            rows = cur.fetchall()
            for row in rows:
                alimento = (
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    str(row[5]).split(","),
                )
                alimentos.append(alimento)

        recetas = []
        with conn.cursor() as cur:
            cur.execute("SELECT nombre, id FROM recetas")
            rows = cur.fetchall()
            for row in rows:
                receta = (row[0].encode('utf-8'), str(row[1]).split(","))
                recetas.append(receta)

        ingredientes = []
        with conn.cursor() as cur:
            cur.execute("SELECT receta_id, alimentacion_id FROM receta_alimentacion")
            rows = cur.fetchall()
            for row in rows:
                ingrediente = (str(row[0]), str(row[1]).split(","))
                ingredientes.append(ingrediente)

        # Cerrar la conexión
        conn.close()

        # CON LIBRERÍA SKLEARN /NO COMPATIBLE CON JYTHON
            # cv = CountVectorizer()
            # count_matrix = cv.fit_transform([alimento[0] for alimento in alimentos])
            # cosine_sim = cosine_similarity(count_matrix)

            # similar_alimentos = list(enumerate(cosine_sim))
            # similar_alimentos = [(alimentos[i[0]][6], i[1]) for i in similar_alimentos]

        # Sin librería sklearn:
        # Lista de matrices de conteo para cada alimento
        count_matrices = [count_words(alimento[0]) for alimento in alimentos]

        # Matriz de similitud del coseno
        cosine_sim = [[cosine_similarity(float(count_matrices[i]), float(count_matrices[j]))
                    for j in range(len(alimentos))] for i in range(len(alimentos))]

        # Lista de tuplas con los alimentos similares
        similar_alimentos = [(alimentos[i][5], cosine_sim[i])
                            for i in range(len(alimentos))]
        print(similar_alimentos)

        want_index = []
        for i in similar_alimentos:
            for x in want:
                if x in i[0]:
                    want_index.append(similar_alimentos.index(i))
        want_index = set(want_index)
        print(want_index)

        similar_indices = set()
        for i in similar_alimentos:
            for x in want_index:
                if i[1][int(x)] > 0.3:
                    similar_indices.update(i[0])

        print(similar_indices) 

        ingredientes_want = set()
        for i in ingredientes:
            for x in similar_indices:
                if x in i[1]:
                    ingredientes_want.update(set(i[0]))

        print(ingredientes_want)

        ing_quitar = set()
        for i in ingredientes:
            for x in want:
                for y in dontwant:
                    if x in i[1] or y in i[1]:
                        ing_quitar.update(set(i[0]))
        print(ing_quitar)

        # Obtener las recetas que contienen los alimentos de want
        recetas_want = set()
        for i in recetas:
            # Verificar que la receta tenga al menos un ingrediente de want
            if any(str(a) in ingredientes_want for a in i[1]) and not any(str(a) in ing_quitar for a in i[1]):
                recetas_want.add(i[0])


        print(recetas_want) #ESTO ES LO QUE HAY QUE DEVOLVER CUANDO COMPRUEBE QUE VA BIEN

        return recetas_want
