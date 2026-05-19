from flask import Flask, request, jsonify

app = Flask(__name__)

# Simulando padrones oficiales
PADRON_SAT_RFC = ["MELJ9505151H2", "XAXX010101000", "JUAN800101ABC"]
PADRON_IMSS_NSS = ["12345678901", "98765432109", "55443322110"]

@app.route('/api/validar-sat', methods=['GET'])
def validar_sat():
    # Obtener el parámetro y limpiar espacios
    rfc_recibido = request.args.get('rfc', '').strip()
    
    if not rfc_recibido:
        return jsonify({"valido": False, "mensaje": "RFC vacio"}), 400
        
    # Comparación exacta en mayúsculas
    existe = rfc_recibido.upper() in PADRON_SAT_RFC
    
    return jsonify({
        "valido": existe,
        "mensaje": "RFC localizado" if existe else "RFC no registrado"
    })

@app.route('/api/validar-imss', methods=['GET'])
def validar_imss():
    # ACTUALIZADO: Obtenemos el parametro nss limpiando espacios vacios accidentales
    nss_recibido = request.args.get('nss', '').strip()
    
    if not nss_recibido:
        return jsonify({"valido": False, "mensaje": "NSS vacio"}), 400
    
    # Verificacion exacta dentro de la lista del seguro social
    existe = nss_recibido in PADRON_IMSS_NSS
    
    return jsonify({
        "valido": existe,
        "mensaje": "NSS localizado" if existe else "NSS no registrado"
    })

if __name__ == '__main__':
    # Ejecuta el servidor en el puerto 5000
    app.run(debug=True, port=5000)