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
        want = [str(x) for x in want]
        dontwant = [str(x) for x in dontwant]

        if not want:
            want = []
        if not dontwant:
            dontwant = []

        # FUNCIONES DE MACHINE LEARNING:

        def cosine_similarity(vector1, vector2):
            dot_product = sum(p*q for p, q in zip(vector1, vector2))
            magnitude1 = math.sqrt(sum([val**2 for val in vector1]))
            magnitude2 = math.sqrt(sum([val**2 for val in vector2]))
            if magnitude1 and magnitude2:
                return dot_product / (magnitude1 * magnitude2)
            else:
                return 0.0

        def create_vector(alimento):
            return [alimento[1], alimento[2], alimento[3], alimento[4], alimento[5]]

        # CONECTAR BASE DE DATOS Y EXTRAER LISTA DE ALIMENTOS Y RECETAS
        conn = jaydebeapi.connect(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/estepper",
            ["estepper", "estepper"],
        )

        alimentos = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT nombre, sal, proteinas, hidratos_de_carbono, fibra_alimentaria, grasas_saturadas, id FROM alimentacion"
            )
            rows = cur.fetchall()
            for row in rows:
                alimento = (
                    row[0],
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    str(row[6]).split(","),
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
            cur.execute(
                "SELECT receta_id, alimentacion_id FROM receta_alimentacion")
            rows = cur.fetchall()
            for row in rows:
                ingrediente = (str(row[0]), str(row[1]).split(","))
                ingredientes.append(ingrediente)

        conn.close()

        # CON LIBRERÍA SKLEARN /NO COMPATIBLE CON JYTHON
        # cv = CountVectorizer()
        # count_matrix = cv.fit_transform([alimento[0] for alimento in alimentos])
        # cosine_sim = cosine_similarity(count_matrix)

        # similar_alimentos = list(enumerate(cosine_sim))
        # similar_alimentos = [(alimentos[i[0]][6], i[1]) for i in similar_alimentos]

        # Sin librería sklearn:
        count_matrices = [create_vector(alimento) for alimento in alimentos]
        cosine_sim = [[cosine_similarity(count_matrices[i], count_matrices[j])
                       for j in range(len(alimentos))] for i in range(len(alimentos))]

        similar_alimentos = [(alimentos[i][6], cosine_sim[i])
                             for i in range(len(alimentos))]

        want_index = []
        for i in similar_alimentos:
            for x in want:
                if x in i[0]:
                    want_index.append(similar_alimentos.index(i))
        want_index = set(want_index)

        similar_indices = set()
        for i in similar_alimentos:
            for x in want_index:
                if i[1][int(x)] > 0.3:
                    similar_indices.update(i[0])

        ingredientes_want = set()
        for i in ingredientes:
            for x in similar_indices:
                if x in i[1]:
                    ingredientes_want.update(set(i[0]))

        ing_quitar = set()
        for i in ingredientes:
            for x in want:
                for y in dontwant:
                    if x in i[1] or y in i[1]:
                        ing_quitar.update(set(i[0]))

        # Recetas que contienen los alimentos seleccionados
        recetas_want = []
        for i in recetas:
            if any(str(a) in ingredientes_want for a in i[1]) and not any(str(a) in ing_quitar for a in i[1]):
                recetas_want.extend(i[1])

        cadena_unida = ','.join(recetas_want)
        cadena_unicode = unicode(cadena_unida, 'utf-8')

        return [cadena_unicode]

    def recomendacionesglobales(self):
        conn = jaydebeapi.connect(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/estepper",
            ["estepper", "estepper"],
        )

        alimentos = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT nombre, id FROM alimentacion"
            )
            rows = cur.fetchall()
            for row in rows:
                alimento = (
                    row[0],
                    str(row[1]).split(",")
                )
                alimentos.append(alimento)
        alimentosconsumidos = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT id_alimento, fecha_consumicion, id FROM alimentosconsumidos")
            rows = cur.fetchall()
            for row in rows:
                receta = (
                    str(row[0]),
                    row[1],
                    str(row[2]).split(","))
                alimentosconsumidos.append(receta)

        recetas = []
        with conn.cursor() as cur:
            cur.execute("SELECT nombre, id FROM recetas")
            rows = cur.fetchall()
            for row in rows:
                receta = (row[0].encode('utf-8'), str(row[1]).split(","))
                recetas.append(receta)

        ingredientes = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT receta_id, alimentacion_id FROM receta_alimentacion")
            rows = cur.fetchall()
            for row in rows:
                ingrediente = (str(row[0]), str(row[1]).split(","))
                ingredientes.append(ingrediente)

        conn.close()

        conteo_alimentos = {}
        for alimento_consumido in alimentosconsumidos:
            alimento_id = alimento_consumido[0]
            if alimento_id in conteo_alimentos:
                conteo_alimentos[alimento_id] += 1
            else:
                conteo_alimentos[alimento_id] = 1

        conteo_alimentos_ordenado = sorted(
            conteo_alimentos.items(), key=lambda x: x[1], reverse=True)
        if len(conteo_alimentos_ordenado) >= 4:
            alimentos_top_4 = [alimento[0]
                               for alimento in conteo_alimentos_ordenado[:4]]
        else:
            alimentos_top_4 = [alimento[0]
                               for alimento in conteo_alimentos_ordenado]

        ingredientes_want = set()
        for i in ingredientes:
            for x in alimentos_top_4:
                if x in i[1]:
                    ingredientes_want.update(set(i[0]))

        recetas_want = []
        for i in recetas:
            if any(str(a) in ingredientes_want for a in i[1]):
                recetas_want.extend(i[1])
        recetas_want = recetas_want[:6]

        cadena_unida = ','.join(recetas_want)
        cadena_unicode = unicode(cadena_unida, 'utf-8')

        return [cadena_unicode]

    def recomendacionesindividuales(self, id):
        conn = jaydebeapi.connect(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/estepper",
            ["estepper", "estepper"],
        )

        alimentos = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT nombre, id FROM alimentacion"
            )
            rows = cur.fetchall()
            for row in rows:
                alimento = (
                    row[0],
                    str(row[1]).split(",")
                )
                alimentos.append(alimento)
        alimentosconsumidos = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT id_alimento, fecha_consumicion, id FROM alimentosconsumidos WHERE id_participante = ?", (id,))
            rows = cur.fetchall()
            for row in rows:
                receta = (
                    str(row[0]),
                    row[1],
                    str(row[2]).split(","))
                alimentosconsumidos.append(receta)

        recetas = []
        with conn.cursor() as cur:
            cur.execute("SELECT nombre, id FROM recetas")
            rows = cur.fetchall()
            for row in rows:
                receta = (row[0].encode('utf-8'), str(row[1]).split(","))
                recetas.append(receta)

        ingredientes = []
        with conn.cursor() as cur:
            cur.execute(
                "SELECT receta_id, alimentacion_id FROM receta_alimentacion")
            rows = cur.fetchall()
            for row in rows:
                ingrediente = (str(row[0]), str(row[1]).split(","))
                ingredientes.append(ingrediente)

        conn.close()

        conteo_alimentos = {}
        for alimento_consumido in alimentosconsumidos:
            alimento_id = alimento_consumido[0]
            if alimento_id in conteo_alimentos:
                conteo_alimentos[alimento_id] += 1
            else:
                conteo_alimentos[alimento_id] = 1

        conteo_alimentos_ordenado = sorted(
            conteo_alimentos.items(), key=lambda x: x[1], reverse=True)
        if len(conteo_alimentos_ordenado) >= 4:
            alimentos_top_4 = [alimento[0]
                               for alimento in conteo_alimentos_ordenado[:4]]
        else:
            alimentos_top_4 = [alimento[0]
                               for alimento in conteo_alimentos_ordenado]

        ingredientes_want = set()
        for i in ingredientes:
            for x in alimentos_top_4:
                if x in i[1]:
                    ingredientes_want.update(set(i[0]))

        recetas_want = []
        for i in recetas:
            if any(str(a) in ingredientes_want for a in i[1]):
                recetas_want.extend(i[1])
        recetas_want = recetas_want[:6]

        cadena_unida = ','.join(recetas_want)
        cadena_unicode = unicode(cadena_unida, 'utf-8')

        return [cadena_unicode]


#PARTE DE NUTRICION

"""
def recomendarAlimentos(fibra, proteina, carbohidratos, grasas, sal, icdr):

    conn = jaydebeapi.connect(
        "com.mysql.jdbc.Driver",
        "jdbc:mysql://localhost:3306/estepper",
        ["estepper", "estepper"],
    )


    # obtener todos los alimentos de la base de datos
    alimentos = []
    with conn.cursor() as cur:
        cur.execute(
            "SELECT nombre, sal, proteinas, hidratos_de_carbono, fibra_alimentaria, grasas_saturadas, id FROM alimentacion"
        )
        rows = cur.fetchall()
        for row in rows:
            alimento = (
                row[0],
                row[1],
                row[2],
                row[3],
                row[4],
                row[5],
                str(row[6]).split(","),
            )
            alimentos.append(alimento)

    n = len(alimentos)

    # Creamos una matriz de n x m para guardar el valor de cada combinación de alimentos
    m = int(icdr)
    dp = [[0 for x in range(m+1)] for y in range(n+1)]

    # Iteramos por cada alimento y por cada cantidad de calorías desde 0 hasta m
    for i in range(1, n+1):
        for j in range(m+1):
            # si el alimento cabe en la mochila, calculamos el valor que aporta y lo sumamos al valor de la combinación anterior
            if alimentos[i-1]['calorias'] <= j:
                valor = alimentos[i-1]['valor'] * (1 - alimentos[i-1]['grasas_saturadas'] / 10) * (1 - alimentos[i-1]['sal'] / 2.3)
                dp[i][j] = max(dp[i-1][j], dp[i-1][j-alimentos[i-1]['calorias']] + valor)

    # Ahora que tenemos el valor de cada combinación, buscamos la combinación que más valor aporta y que cumple las restricciones
    res = []
    i = n
    j = m
    while i > 0 and j > 0:
        if dp[i][j] == dp[i-1][j]:
            i -= 1
        else:
            res.append(alimentos[i-1])
            j -= alimentos[i-1]['calorias']
            i -= 1

    # Devolvemos los alimentos recomendados ordenados por cantidad recomendada de consumo
    res.reverse()

    return res"""

    


