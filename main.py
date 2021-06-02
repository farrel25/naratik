from flask import Flask, jsonify
from flask import request as req
import numpy as np
import requests
import tensorflow as tf
from keras_preprocessing import image
from tensorflow.keras.applications.inception_v3 import preprocess_input
from tensorflow.keras.applications.mobilenet import preprocess_input as preprocess_input_technique
from io import BytesIO
import PIL
from PIL import Image
from urllib import request
import re
import hashlib
import json
from firebase_admin import credentials, initialize_app, firestore


app = Flask(__name__)
data_json_motif = {}
data_json_technique = {}
env_var = open("var.json")
loaded_var = json.load(env_var)
super_secret_key = loaded_var["Auth"]
base_url = loaded_var["base_url"]
add_endpoint = loaded_var["add_endpoint"]


cred = credentials.Certificate("key.json")
default_app = initialize_app(cred)
db = firestore.client()
motif_collection = db.collection('motif')
technique_collection = db.collection('technique')


def load_model_motif():
    model = tf.keras.models.load_model("model.h5")
    return model


def making_prediction_motif(filename):
    labels = (['ceplok', 'kawung', 'megamendung', 'parang', 'sidomukti'])
    url = base_url + filename
    document_id = re.sub(r"\.\w*", "", filename)
    data_json_motif.update({"id": document_id})
    data_json_motif.update({"img_url": url})

    res = request.urlopen(url).read()
    img = Image.open(BytesIO(res)).resize((224, 224))
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    x = preprocess_input(x)

    images = np.vstack([x])
    model = load_model_motif()
    proba = model.predict(images)[0]
    array_motif = []

    for (label, p) in zip(labels, proba):
        # print("{}: {:.2f}%".format(label, p * 100))
        value = round((p * 100), 2)
        array_motif.append({"motifName": label, "value": value})
    data_json_motif.update({"motif": array_motif})
    return data_json_motif


def load_model_technique():
    model_technique = tf.keras.models.load_model("model_technique.h5")
    return model_technique


def making_prediction_technique(filename):
    labels = (['modern', 'tradisional'])
    url = base_url + filename
    document_id = re.sub(r"\.\w*", "", filename)
    data_json_technique.update({"id": document_id})
    data_json_technique.update({"img_url": url})

    res = request.urlopen(url).read()
    img = Image.open(BytesIO(res)).resize((300, 300))
    x = image.img_to_array(img)
    x = np.expand_dims(x, axis=0)
    x = preprocess_input_technique(x)

    images = np.vstack([x])
    model = load_model_technique()
    proba = model.predict(images)[0]
    array_technique = []

    for (label, p) in zip(labels, proba):
        # print("{}: {:.2f}%".format(label, p * 100))
        value = round((p * 100), 2)
        array_technique.append({"techniqueName": label, "value": value})
    data_json_technique.update({"technique": array_technique})
    return data_json_technique


'''
def post_prediction(data):
    x = requests.post(add_endpoint, json=data)
    return x'''


def post_prediction_motif_result(json_data):
    try:
        # content = req.get_json()
        document_id = json_data["id"]
        motif_collection.document(document_id).set(json_data)
        return json_data
    except Exception as e:
        return f"An Error Occured: {e}"


def post_prediction_technique_result(json_data):
    try:
        # content = req.get_json()
        document_id = json_data["id"]
        technique_collection.document(document_id).set(json_data)
        return json_data
    except Exception as e:
        return f"An Error Occured: {e}"


@app.route('/', methods=["GET"])
def hello():
    try:
        auth = req.headers.get("Auth")
        encoded = auth.encode()
        auth_hash = hashlib.sha256(encoded).hexdigest()
        if auth_hash == super_secret_key:
            return "Hello World!"
        else:
            return "No Access"
    except Exception as e:
        return f"An Error Occured: {e}"


@app.route('/<filename>', methods=["GET"])
def predict(filename):
    try:
        auth = req.headers.get("Auth")
        encoded = auth.encode()
        auth_hash = hashlib.sha256(encoded).hexdigest()
        if auth_hash == super_secret_key:
            x = making_prediction_motif(filename)
            # post_prediction(x)
            post_prediction_motif_result(x)
            y = making_prediction_technique(filename)
            post_prediction_technique_result(y)
            return "ok"
        else:
            return "No Access"
    except Exception as e:
        return f"An Error Occured: {e}"


@app.route('/motif/<unique_id>', methods=["GET"])
def get_predict_motif_result(unique_id):
    try:
        document_id = unique_id
        data = motif_collection.document(document_id).get()
        return jsonify(data.to_dict()), 200
    except Exception as e:
        return f"An Error Occured: {e}"


@app.route('/technique/<unique_id>', methods=["GET"])
def get_predict_technique_result(unique_id):
    try:
        document_id = unique_id
        data = technique_collection.document(document_id).get()
        return jsonify(data.to_dict()), 200
    except Exception as e:
        return f"An Error Occured: {e}"

@app.route('/shop/', methods=["GET"])
def get_shop_data():
    try:
        docs = db.collection("shop").stream()
        array_shop = []
        for doc in docs:
            array_shop.append({"namaToko":doc.get("namaToko"),"alamatToko": doc.get("alamatToko"), "product":doc.get("produk")})
        data_json_shop.update({"shopList": array_shop})
        return data_json_shop
    except Exception as e:
        return f"An Error Occured: {e}"

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8080, debug=True)
