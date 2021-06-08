# REST API For Predict Batik Motif & Technique Using Python Flask
## Overview
This application is a solution so that the prediction process carried out by the TensorFlow model can be carried out with computing in the cloud, where the prediction results will be stored in Google Firestore and can be accessed through one of the endpoints in this application.

We use Flask, a microframework for python to create a web application. Flask makes it easy for us to build REST APIs because this framework has a low learning curve and makes routing very easy. We use firebase library to access and store data to our firestore database.

## How To Install
1. Clone this repository.
2. Make sure you're inside cloud-prediction-python directory.
3. Get your own key.json from Firebase Service Account.
4. Put pre-trained model into this folder, name it "motif.h5" for the motif prediction model and "model_technique.h5" for the technique prediction model.
5. pip install -r requirements.txt

## How To Run
## On windows

Open powershell and type:

    set FLASK_APP = "main.py"
    flask run
## On linux based machine

Open bash and type:
  
    export FLASK_APP=main.py
    flask run
## Endpoints
- GET '/<filename>' : This endpoint start the machine learning prediction process and store the result on firestore. This endpoint require Authentication header that only our internal team members knows the key. Will response with "ok" or an error code.
- GET '/motif/<unique_id> : This endpoint responses with JSON about the motif prediction result of the requested unique id.
- GET '/technique/<unique_id> : This endpoint responses with JSON about the technique prediction result of the requested unique id.
- GET '/shop/ : This endpoint responses with list of batik shop in JSON format.
## Testing The Application
1. We will use POSTMAN to test this application.
2. Start your application. (Refer to: How To Run section of the documentation).
3. Your application should be running on 127.0.0.1:5000/
4. Set the HTTP Request to GET and set http://127.0.0.1:5000/ as the URL.
5. We will use 2xeWe24 as unique_id for demonstration purposes.
6. Change URL to http://127.0.0.1:5000/motif/2xeWe24 then click Send.
7. You should get a JSON response.
8. Repeat step 6 for another open endpoint(motif,technique,shop).
    